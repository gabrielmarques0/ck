package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.metric.CKASTVisitor;
import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import com.github.mauricioaniche.ck.util.JDTUtils;
import org.eclipse.jdt.core.dom.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Callable;

import static com.github.mauricioaniche.ck.util.LOCCalculator.calculate;

public class CKVisitor extends ASTVisitor {

	private String sourceFilePath;
	private int anonymousNumber;
	private int initializerNumber;

	class MethodInTheStack {
		CKMethodResult result;
		List<MethodLevelMetric> methodLevelMetrics;
	}

	class ClassInTheStack {
		CKClassResult result;
		List<ClassLevelMetric> classLevelMetrics;
		Stack<MethodInTheStack> methods;

		ClassInTheStack() {
			methods = new Stack<>();
		}
	}

	private Stack<ClassInTheStack> classes;

	private Set<CKClassResult> collectedClasses;

	private CompilationUnit cu;
	private Callable<List<ClassLevelMetric>> classLevelMetrics;
	private Callable<List<MethodLevelMetric>> methodLevelMetrics;

	public CKVisitor(String sourceFilePath, CompilationUnit cu, Callable<List<ClassLevelMetric>> classLevelMetrics,
			Callable<List<MethodLevelMetric>> methodLevelMetrics) {
		this.sourceFilePath = sourceFilePath;
		this.cu = cu;
		this.classLevelMetrics = classLevelMetrics;
		this.methodLevelMetrics = methodLevelMetrics;
		this.classes = new Stack<>();
		this.collectedClasses = new HashSet<>();
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		ITypeBinding binding = node.resolveBinding();

		// build a CKClassResult based on the current type
		// declaration we are visiting
		String className = binding != null ? binding.getBinaryName() : node.getName().getFullyQualifiedName();
		String type = getTypeOfTheUnit(node);
		int modifiers = node.getModifiers();
		CKClassResult currentClass = new CKClassResult(sourceFilePath, className, type, modifiers);
		currentClass.setLoc(calculate(node.toString()));

		// there might be metrics that use it
		// (even before a class is declared)
		metricsVisit(node);

		// create a set of visitors, just for the current class
		List<ClassLevelMetric> classLevelMetrics = instantiateClassLevelMetricVisitors(className);

		// store everything in a 'class in the stack' data structure
		ClassInTheStack classInTheStack = new ClassInTheStack();
		classInTheStack.result = currentClass;
		classInTheStack.classLevelMetrics = classLevelMetrics;

		// push it to the stack, so we know the current class we are visiting
		classes.push(classInTheStack);

		// there might be class level metrics that use the TypeDeclaration
		// so, let's run them
		classes.peek().classLevelMetrics.stream().map(metric -> (CKASTVisitor) metric).forEach(ast -> ast.visit(node));

		return true;
	}

	@Override
	public void endVisit(TypeDeclaration node) {

		// let's first visit any metrics that might make use of this endVisit
		classes.peek().classLevelMetrics.stream().map(metric -> (CKASTVisitor) metric)
				.forEach(ast -> ast.endVisit(node));

		ClassInTheStack completedClass = classes.pop();

		// persist the results of the class level metrics in the result
		completedClass.classLevelMetrics.forEach(m -> m.setResult(completedClass.result));

		// we are done processing this class, so now let's
		// store it in the collected classes set
		collectedClasses.add(completedClass.result);
	}

	public boolean visit(MethodDeclaration node) {

		IMethodBinding binding = node.resolveBinding();
		String currentMethodName = JDTUtils.getMethodFullName(node);
		String currentQualifiedMethodName = JDTUtils.getQualifiedMethodFullName(node);
		boolean isConstructor = node.isConstructor();

		String className = ((currentQualifiedMethodName.lastIndexOf(currentMethodName) - 1) > 0)
				? currentQualifiedMethodName.substring(0,
						(currentQualifiedMethodName.lastIndexOf(currentMethodName) - 1))
				: "";

		CKMethodResult currentMethod = new CKMethodResult(currentMethodName, currentQualifiedMethodName, isConstructor,
				node.getModifiers());
		currentMethod.setLoc(calculate(node.toString()));
		currentMethod.setStartLine(JDTUtils.getStartLine(cu, node));

		// let's instantiate method level visitors for this current method
		List<MethodLevelMetric> methodLevelMetrics = instantiateMethodLevelMetricVisitors(currentQualifiedMethodName);

		// we add it to the current class we are visiting
		MethodInTheStack methodInTheStack = new MethodInTheStack();
		methodInTheStack.result = currentMethod;
		methodInTheStack.methodLevelMetrics = methodLevelMetrics;
		classes.peek().methods.push(methodInTheStack);

		// and there might be metrics that also use the methoddeclaration node.
		// so, let's call them
		metricsVisit(node);

		return true;
	}

	@Override
	public void endVisit(MethodDeclaration node) {

		// let's first invoke the metrics, because they might use this node
		metricsEndVisit(node);

		// remove the method from the stack
		MethodInTheStack completedMethod = classes.peek().methods.pop();

		// persist the data of the visitors in the CKMethodResult
		completedMethod.methodLevelMetrics.forEach(m -> m.setResult(completedMethod.result));

		// store its final version in the current class
		classes.peek().result.addMethod(completedMethod.result);
	}

	public boolean visit(AnonymousClassDeclaration node) {
		java.util.List<String> stringList = new java.util.ArrayList<>();
		stringList = stringList.stream().map(string -> string.toString()).collect(java.util.stream.Collectors.toList());

		// there might be metrics that use it
		// (even before an anonymous class is created)
		metricsVisit(node);

		// we give the anonymous class a 'class$AnonymousN' name
		String anonClassName = classes.peek().result.getClassName() + "$Anonymous" + ++anonymousNumber;
		CKClassResult currentClass = new CKClassResult(sourceFilePath, anonClassName, "anonymous", -1);
		currentClass.setLoc(calculate(node.toString()));

		// create a set of visitors, just for the current class
		List<ClassLevelMetric> classLevelMetrics = instantiateClassLevelMetricVisitors(anonClassName);

		// store everything in a 'class in the stack' data structure
		ClassInTheStack classInTheStack = new ClassInTheStack();
		classInTheStack.result = currentClass;
		classInTheStack.classLevelMetrics = classLevelMetrics;

		// push it to the stack, so we know the current class we are visiting
		classes.push(classInTheStack);

		// and there might be metrics that also use the methoddeclaration node.
		// so, let's call them
		metricsVisit(node);

		return true;
	}

	public void endVisit(AnonymousClassDeclaration node) {

		classes.peek().classLevelMetrics.stream().map(metric -> (CKASTVisitor) metric)
				.forEach(ast -> ast.endVisit(node));

		ClassInTheStack completedClass = classes.pop();

		// persist the results of the class level metrics in the result
		completedClass.classLevelMetrics.forEach(m -> m.setResult(completedClass.result));

		// we are done processing this class, so now let's
		// store it in the collected classes set
		collectedClasses.add(completedClass.result);
	}

	// static blocks
	public boolean visit(Initializer node) {

		String currentMethodName = "(initializer " + (++initializerNumber) + ")";

		CKMethodResult currentMethod = new CKMethodResult(currentMethodName, currentMethodName, false,
				node.getModifiers());
		currentMethod.setLoc(calculate(node.toString()));
		currentMethod.setStartLine(JDTUtils.getStartLine(cu, node));

		// let's instantiate method level visitors for this current method
		List<MethodLevelMetric> methodLevelMetrics = instantiateMethodLevelMetricVisitors(currentMethodName);

		// we add it to the current class we are visiting
		MethodInTheStack methodInTheStack = new MethodInTheStack();
		methodInTheStack.result = currentMethod;
		methodInTheStack.methodLevelMetrics = methodLevelMetrics;
		classes.peek().methods.push(methodInTheStack);

		// and there might be metrics that also use the methoddeclaration node.
		// so, let's call them
		metricsVisit(node);

		return true;
	}

	@Override
	public void endVisit(Initializer node) {

		// let's first invoke the metrics, because they might use this node
		metricsEndVisit(node);

		// remove the method from the stack
		MethodInTheStack completedMethod = classes.peek().methods.pop();

		// persist the data of the visitors in the CKMethodResult
		completedMethod.methodLevelMetrics.forEach(m -> m.setResult(completedMethod.result));

		// store its final version in the current class
		classes.peek().result.addMethod(completedMethod.result);
	}

	public boolean visit(EnumDeclaration node) {
		ITypeBinding binding = node.resolveBinding();

		// there might be metrics that use it
		// (even before a enum is declared)
		metricsVisit(node);

		// build a CKClassResult based on the current type
		// declaration we are visiting
		String className = binding != null ? binding.getBinaryName() : node.getName().getFullyQualifiedName();
		String type = "enum";
		int modifiers = node.getModifiers();
		CKClassResult currentClass = new CKClassResult(sourceFilePath, className, type, modifiers);
		currentClass.setLoc(calculate(node.toString()));

		// create a set of visitors, just for the current class
		List<ClassLevelMetric> classLevelMetrics = instantiateClassLevelMetricVisitors(className);

		// store everything in a 'class in the stack' data structure
		ClassInTheStack classInTheStack = new ClassInTheStack();
		classInTheStack.result = currentClass;
		classInTheStack.classLevelMetrics = classLevelMetrics;

		// push it to the stack, so we know the current class we are visiting
		classes.push(classInTheStack);

		// there might be class level metrics that use the TypeDeclaration
		// so, let's run them
		classes.peek().classLevelMetrics.stream().map(metric -> (CKASTVisitor) metric).forEach(ast -> ast.visit(node));

		return true;

	}

	@Override
	public void endVisit(EnumDeclaration node) {
		// let's first visit any metrics that might make use of this endVisit
		classes.peek().classLevelMetrics.stream().map(metric -> (CKASTVisitor) metric)
				.forEach(ast -> ast.endVisit(node));

		ClassInTheStack completedClass = classes.pop();

		// persist the results of the class level metrics in the result
		completedClass.classLevelMetrics.forEach(m -> m.setResult(completedClass.result));

		// we are done processing this class, so now let's
		// store it in the collected classes set
		collectedClasses.add(completedClass.result);
	}

	private List<ClassLevelMetric> instantiateClassLevelMetricVisitors(String className) {
		try {
			List<ClassLevelMetric> classes = classLevelMetrics.call();
			classes.forEach(c -> {
				c.setClassName(className);
			});
			return classes;
		} catch (Exception e) {
			throw new RuntimeException("Could not instantiate class level visitors", e);
		}
	}

	private List<MethodLevelMetric> instantiateMethodLevelMetricVisitors(String methodName) {
		try {
			List<MethodLevelMetric> methods = methodLevelMetrics.call();
			methods.forEach(m -> {
				m.setMethodName(methodName);
			});
			return methods;
		} catch (Exception e) {
			throw new RuntimeException("Could not instantiate method level visitors", e);
		}
	}

	public Set<CKClassResult> getCollectedClasses() {
		return collectedClasses;
	}

	private String getTypeOfTheUnit(TypeDeclaration node) {
		return node.isInterface() ? "interface" : (classes.isEmpty() ? "class" : "innerclass");
	}

	private boolean metricsVisit(ASTNode node) {
		if (classes.isEmpty())
			return true;

		classes.peek().classLevelMetrics.stream().map(metric -> (CKASTVisitor) metric).forEach(ast -> ast.visit(node));
		if (classes.peek().methods.isEmpty())
			return true;

		classes.peek().methods.peek().methodLevelMetrics.stream().map(metric -> (CKASTVisitor) metric)
				.forEach(ast -> ast.visit(node));
		return true;
	}

	private void metricsEndVisit(ASTNode node) {
		if (classes.isEmpty())
			return;

		classes.peek().classLevelMetrics.stream().map(metric -> (CKASTVisitor) metric)
				.forEach(ast -> ast.endVisit(node));
		if (classes.peek().methods.isEmpty())
			return;

		classes.peek().methods.peek().methodLevelMetrics.stream().map(metric -> (CKASTVisitor) metric)
				.forEach(ast -> ast.endVisit(node));
	}

	// -------------------------------------------------------
	// From here, just delegating the calls to the metrics
	public boolean visit(AnnotationTypeDeclaration node) {
		return metricsVisit(node);
	}

	public boolean visit(AnnotationTypeMemberDeclaration node) {
		return metricsVisit(node);
	}

	public boolean visit(ArrayAccess node) {
		return metricsVisit(node);
	}

	public boolean visit(ArrayCreation node) {
		return metricsVisit(node);
	}

	public boolean visit(ArrayInitializer node) {
		return metricsVisit(node);
	}

	public boolean visit(ArrayType node) {
		return metricsVisit(node);
	}

	public boolean visit(AssertStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(Assignment node) {
		return metricsVisit(node);
	}

	public boolean visit(Block node) {
		return metricsVisit(node);
	}

	public boolean visit(BlockComment node) {
		return metricsVisit(node);
	}

	public boolean visit(BooleanLiteral node) {
		return metricsVisit(node);
	}

	public boolean visit(BreakStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(CastExpression node) {
		return metricsVisit(node);
	}

	public boolean visit(CatchClause node) {
		return metricsVisit(node);
	}

	public boolean visit(CharacterLiteral node) {
		return metricsVisit(node);
	}

	public boolean visit(ClassInstanceCreation node) {
		return metricsVisit(node);
	}

	public boolean visit(CompilationUnit node) {
		return metricsVisit(node);
	}

	public boolean visit(ConditionalExpression node) {
		return metricsVisit(node);
	}

	public boolean visit(ConstructorInvocation node) {
		return metricsVisit(node);
	}

	public boolean visit(ContinueStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(CreationReference node) {
		return metricsVisit(node);
	}

	public boolean visit(Dimension node) {
		return metricsVisit(node);
	}

	public boolean visit(DoStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(EmptyStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(EnhancedForStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(EnumConstantDeclaration node) {
		return metricsVisit(node);
	}

	public boolean visit(ExpressionMethodReference node) {
		return metricsVisit(node);
	}

	public boolean visit(ExpressionStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(FieldAccess node) {
		return metricsVisit(node);
	}

	public boolean visit(FieldDeclaration node) {
		return metricsVisit(node);
	}

	public boolean visit(ForStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(IfStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(ImportDeclaration node) {
		return metricsVisit(node);
	}

	public boolean visit(InfixExpression node) {
		return metricsVisit(node);
	}

	public boolean visit(InstanceofExpression node) {
		return metricsVisit(node);
	}

	public boolean visit(IntersectionType node) {
		return metricsVisit(node);
	}

	public boolean visit(LabeledStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(LambdaExpression node) {
		return metricsVisit(node);
	}

	public boolean visit(LineComment node) {
		return metricsVisit(node);
	}

	public boolean visit(MarkerAnnotation node) {
		return metricsVisit(node);
	}

	public boolean visit(MemberRef node) {
		return metricsVisit(node);
	}

	public boolean visit(MemberValuePair node) {
		return metricsVisit(node);
	}

	public boolean visit(MethodRef node) {
		return metricsVisit(node);
	}

	public boolean visit(MethodRefParameter node) {
		return metricsVisit(node);
	}

	public boolean visit(MethodInvocation node) {
		return metricsVisit(node);
	}

	public boolean visit(Modifier node) {
		return metricsVisit(node);
	}

	public boolean visit(NameQualifiedType node) {
		return metricsVisit(node);
	}

	public boolean visit(NormalAnnotation node) {
		return metricsVisit(node);
	}

	public boolean visit(NullLiteral node) {
		return metricsVisit(node);
	}

	public boolean visit(NumberLiteral node) {
		return metricsVisit(node);
	}

	public boolean visit(PackageDeclaration node) {
		return metricsVisit(node);
	}

	public boolean visit(ParameterizedType node) {
		return metricsVisit(node);
	}

	public boolean visit(ParenthesizedExpression node) {
		return metricsVisit(node);
	}

	public boolean visit(PostfixExpression node) {
		return metricsVisit(node);
	}

	public boolean visit(PrefixExpression node) {
		return metricsVisit(node);
	}

	public boolean visit(PrimitiveType node) {
		return metricsVisit(node);
	}

	public boolean visit(QualifiedName node) {
		return metricsVisit(node);
	}

	public boolean visit(QualifiedType node) {
		return metricsVisit(node);
	}

	public boolean visit(ReturnStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(SimpleName node) {
		return metricsVisit(node);
	}

	public boolean visit(SimpleType node) {
		return metricsVisit(node);
	}

	public boolean visit(SingleMemberAnnotation node) {
		return metricsVisit(node);
	}

	public boolean visit(SingleVariableDeclaration node) {
		return metricsVisit(node);
	}

	public boolean visit(StringLiteral node) {
		return metricsVisit(node);
	}

	public boolean visit(SuperConstructorInvocation node) {
		return metricsVisit(node);
	}

	public boolean visit(SuperFieldAccess node) {
		return metricsVisit(node);
	}

	public boolean visit(SuperMethodInvocation node) {
		return metricsVisit(node);
	}

	public boolean visit(SuperMethodReference node) {
		return metricsVisit(node);
	}

	public boolean visit(SwitchCase node) {
		return metricsVisit(node);
	}

	public boolean visit(SwitchStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(SynchronizedStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(TagElement node) {
		return metricsVisit(node);
	}

	public boolean visit(TextElement node) {
		return metricsVisit(node);
	}

	public boolean visit(ThisExpression node) {
		return metricsVisit(node);
	}

	public boolean visit(ThrowStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(TryStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(TypeDeclarationStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(TypeLiteral node) {
		return metricsVisit(node);
	}

	public boolean visit(TypeMethodReference node) {
		return metricsVisit(node);
	}

	public boolean visit(TypeParameter node) {
		return metricsVisit(node);
	}

	public boolean visit(UnionType node) {
		return metricsVisit(node);
	}

	public boolean visit(VariableDeclarationExpression node) {
		return metricsVisit(node);
	}

	public boolean visit(VariableDeclarationStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(VariableDeclarationFragment node) {
		return metricsVisit(node);
	}

	public boolean visit(WhileStatement node) {
		return metricsVisit(node);
	}

	public boolean visit(WildcardType node) {
		return metricsVisit(node);
	}

	// we only visit if we found a type already.
	// TODO: understand what happens with a javadoc in a class. Will the
	// TypeDeclaration come first?
	public boolean visit(Javadoc node) {
		return metricsVisit(node);
	}

	// ---------------------------------------------
	// End visits

	@Override
	public void endVisit(Block node) {
		metricsEndVisit(node);
	}

	@Override
	public void endVisit(FieldAccess node) {
		metricsEndVisit(node);
	}

	@Override
	public void endVisit(ConditionalExpression node) {
		metricsEndVisit(node);
	}

	public void endVisit(ForStatement node) {
		metricsEndVisit(node);
	}

	public void endVisit(EnhancedForStatement node) {
		metricsEndVisit(node);
	}

	public void endVisit(DoStatement node) {
		metricsEndVisit(node);
	}

	public void endVisit(WhileStatement node) {
		metricsEndVisit(node);
	}

	public void endVisit(SwitchCase node) {
		metricsEndVisit(node);
	}

	public void endVisit(IfStatement node) {
		metricsEndVisit(node);
	}

	public void endVisit(SwitchStatement node) {
		metricsEndVisit(node);
	}

	public void endVisit(CatchClause node) {
		metricsEndVisit(node);
	}

	public void endVisit(Javadoc node) {
		metricsEndVisit(node);
	}

	public void endVisit(QualifiedName node) {
		metricsEndVisit(node);
	}
	// TODO: add all other endVisit blocks
}
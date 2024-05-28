package com.github.mauricioaniche.ck;

import com.github.mauricioaniche.ck.metric.CKASTVisitor;
import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import org.eclipse.jdt.core.dom.*;

public class ASTDebugger implements CKASTVisitor, ClassLevelMetric {

	@Override
	public void visit(AnnotationTypeDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(AnnotationTypeMemberDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(AnonymousClassDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ArrayAccess node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ArrayCreation node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ArrayInitializer node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ArrayType node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(AssertStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(Assignment node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(Block node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(BlockComment node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(BooleanLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(BreakStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(CastExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(CatchClause node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(CharacterLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ClassInstanceCreation node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(CompilationUnit node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ConditionalExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ConstructorInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ContinueStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(CreationReference node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(Dimension node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(DoStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(EmptyStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(EnhancedForStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(EnumConstantDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(EnumDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ExpressionMethodReference node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ExpressionStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(FieldAccess node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(FieldDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ForStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(IfStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ImportDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(InfixExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(Initializer node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(InstanceofExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(IntersectionType node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(LabeledStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(LambdaExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(LineComment node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(MarkerAnnotation node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(MemberRef node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(MemberValuePair node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(MethodRef node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(MethodRefParameter node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(MethodDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(MethodInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(Modifier node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(NameQualifiedType node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(NormalAnnotation node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(NullLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(NumberLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(PackageDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ParameterizedType node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ParenthesizedExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(PostfixExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(PrefixExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(PrimitiveType node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(QualifiedName node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(QualifiedType node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ReturnStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(SimpleName node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(SimpleType node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(SingleMemberAnnotation node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(SingleVariableDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(StringLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(SuperConstructorInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(SuperFieldAccess node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(SuperMethodInvocation node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(SuperMethodReference node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(SwitchCase node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(SwitchStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(SynchronizedStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(TagElement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(TextElement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ThisExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(ThrowStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(TryStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(TypeDeclaration node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(TypeDeclarationStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(TypeLiteral node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(TypeMethodReference node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(TypeParameter node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(UnionType node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(VariableDeclarationExpression node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(VariableDeclarationStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(VariableDeclarationFragment node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(WhileStatement node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void visit(WildcardType node) {
		System.out.println("-- " + node.getClass().getSimpleName());
		System.out.println(node.toString());
	}

	@Override
	public void setResult(CKClassResult result) {

	}
}

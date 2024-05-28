package com.github.mauricioaniche.ck.metric;

import org.eclipse.jdt.core.dom.*;

public interface CKASTVisitor extends CKASTDeclarationVisitor, CKASTExpressionVisitor, CKASTModuleVisitor,
    CKASTTypeVisitor, CKASTStatementVisitor, CKASTLiteralVisitor {
  default void visit(VariableDeclarationFragment node) {
  }

  default void visit(Initializer node) {
  }

  default void visit(FieldAccess node) {
  }

  default void visit(QualifiedName node) {
  }

  default void visit(SimpleName node) {
  }

  default void visit(NormalAnnotation node) {
  }

  default void visit(MarkerAnnotation node) {
  }

  default void visit(SingleMemberAnnotation node) {
  }

  default void visit(SingleVariableDeclaration node) {
  }

  default void visit(SuperMethodInvocation node) {
  }

  default void visit(CompilationUnit node) {
  }

  default void visit(ConstructorInvocation node) {
  }

  default void visit(CreationReference node) {
  }

  default void visit(Dimension node) {
  }

  default void visit(ExpressionMethodReference node) {
  }

  default void visit(ExpressionStatement node) {
  }

  default void visit(ImportDeclaration node) {
  }

  default void visit(MemberRef node) {
  }

  default void visit(MemberValuePair node) {
  }

  default void visit(MethodRef node) {
  }

  default void visit(MethodRefParameter node) {
  }

  default void visit(Modifier node) {
  }

  default void visit(SuperConstructorInvocation node) {
  }

  default void visit(SuperFieldAccess node) {
  }

  default void visit(SuperMethodReference node) {
  }

  default void visit(TagElement node) {
  }

  default void visit(TextElement node) {
  }

  default void visit(TypeMethodReference node) {
  }

  default void visit(TypeParameter node) {
  }

  default void visit(Javadoc node) {
  }

  default void endVisit(VariableDeclarationFragment node) {
  }

  default void endVisit(Initializer node) {
  }

  default void endVisit(FieldAccess node) {
  }

  default void endVisit(QualifiedName node) {
  }

  default void endVisit(SimpleName node) {
  }

  default void endVisit(NormalAnnotation node) {
  }

  default void endVisit(MarkerAnnotation node) {
  }

  default void endVisit(SingleMemberAnnotation node) {
  }

  default void endVisit(SingleVariableDeclaration node) {
  }

  default void endVisit(SuperMethodInvocation node) {
  }

  default void endVisit(CompilationUnit node) {
  }

  default void endVisit(ConstructorInvocation node) {
  }

  default void endVisit(CreationReference node) {
  }

  default void endVisit(Dimension node) {
  }

  default void endVisit(ExpressionMethodReference node) {
  }

  default void endVisit(ExpressionStatement node) {
  }

  default void endVisit(ImportDeclaration node) {
  }

  default void endVisit(MemberRef node) {
  }

  default void endVisit(MemberValuePair node) {
  }

  default void endVisit(MethodRef node) {
  }

  default void endVisit(MethodRefParameter node) {
  }

  default void endVisit(Modifier node) {
  }

  default void endVisit(SuperConstructorInvocation node) {
  }

  default void endVisit(SuperFieldAccess node) {
  }

  default void endVisit(SuperMethodReference node) {
  }

  default void endVisit(TagElement node) {
  }

  default void endVisit(TextElement node) {
  }

  default void endVisit(TypeMethodReference node) {
  }

  default void endVisit(TypeParameter node) {
  }

  default void endVisit(Javadoc node) {
  }
}

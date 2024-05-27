package com.github.mauricioaniche.ck.metric;

import org.eclipse.jdt.core.dom.*;

public interface CKASTExpressionVisitor {
    default void visit(ArrayAccess node) {}
    default void visit(ArrayCreation node) {}
    default void visit(ArrayInitializer node) {}
    default void visit(Assignment node) {}
    default void visit(CastExpression node) {}
    default void visit(ClassInstanceCreation node) {}
    default void visit(ConditionalExpression node) {}
    default void visit(InfixExpression node) {}
    default void visit(InstanceofExpression node) {}
    default void visit(LambdaExpression node) {}
    default void visit(MethodInvocation node) {}
    default void visit(NameQualifiedType node) {}
    default void visit(ParenthesizedExpression node) {}
    default void visit(PostfixExpression node) {}
    default void visit(PrefixExpression node) {}
    default void visit(ThisExpression node) {}
    default void visit(TypeLiteral node) {}
    default void visit(VariableDeclarationExpression node) {}

    default void endVisit(ArrayAccess node) {}
    default void endVisit(ArrayCreation node) {}
    default void endVisit(ArrayInitializer node) {}
    default void endVisit(Assignment node) {}
    default void endVisit(CastExpression node) {}
    default void endVisit(ClassInstanceCreation node) {}
    default void endVisit(ConditionalExpression node) {}
    default void endVisit(InfixExpression node) {}
    default void endVisit(InstanceofExpression node) {}
    default void endVisit(LambdaExpression node) {}
    default void endVisit(MethodInvocation node) {}
    default void endVisit(NameQualifiedType node) {}
    default void endVisit(ParenthesizedExpression node) {}
    default void endVisit(PostfixExpression node) {}
    default void endVisit(PrefixExpression node) {}
    default void endVisit(ThisExpression node) {}
    default void endVisit(TypeLiteral node) {}
    default void endVisit(VariableDeclarationExpression node) {}
}

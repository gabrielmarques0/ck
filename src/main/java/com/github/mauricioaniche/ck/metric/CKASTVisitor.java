package com.github.mauricioaniche.ck.metric;

import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.Initializer;

public interface CKASTVisitor extends CKASTDeclarationVisitor, CKASTExpressionVisitor, CKASTModuleVisitor, CKASTTypeVisitor, CKASTStatementVisitor, CKASTLiteralVisitor {
    default void visit(VariableDeclarationFragment node) {}
    default void visit(Initializer node) {}
}

package com.github.mauricioaniche.ck.metric;

import org.eclipse.jdt.core.dom.*;

public interface CKASTStatementVisitor {
    default void visit(AssertStatement node) {}
    default void visit(Block node) {}
    default void visit(BreakStatement node) {}
    default void visit(CatchClause node) {}
    default void visit(ContinueStatement node) {}
    default void visit(DoStatement node) {}
    default void visit(EmptyStatement node) {}
    default void visit(EnhancedForStatement node) {}
    default void visit(ForStatement node) {}
    default void visit(IfStatement node) {}
    default void visit(LabeledStatement node) {}
    default void visit(ReturnStatement node) {}
    default void visit(SwitchCase node) {}
    default void visit(SwitchStatement node) {}
    default void visit(SynchronizedStatement node) {}
    default void visit(ThrowStatement node) {}
    default void visit(TryStatement node) {}
    default void visit(VariableDeclarationStatement node) {}
    default void visit(WhileStatement node) {}

    default void endVisit(AssertStatement node) {}
    default void endVisit(Block node) {}
    default void endVisit(BreakStatement node) {}
    default void endVisit(CatchClause node) {}
    default void endVisit(ContinueStatement node) {}
    default void endVisit(DoStatement node) {}
    default void endVisit(EmptyStatement node) {}
    default void endVisit(EnhancedForStatement node) {}
    default void endVisit(ForStatement node) {}
    default void endVisit(IfStatement node) {}
    default void endVisit(LabeledStatement node) {}
    default void endVisit(ReturnStatement node) {}
    default void endVisit(SwitchCase node) {}
    default void endVisit(SwitchStatement node) {}
    default void endVisit(SynchronizedStatement node) {}
    default void endVisit(ThrowStatement node) {}
    default void endVisit(TryStatement node) {}
    default void endVisit(VariableDeclarationStatement node) {}
    default void endVisit(WhileStatement node) {}
}

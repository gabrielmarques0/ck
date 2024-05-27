package com.github.mauricioaniche.ck.metric;

import org.eclipse.jdt.core.dom.*;

public interface CKASTLiteralVisitor {
    default void visit(BooleanLiteral node) {}
    default void visit(CharacterLiteral node) {}
    default void visit(NullLiteral node) {}
    default void visit(NumberLiteral node) {}
    default void visit(StringLiteral node) {}
    default void visit(BlockComment node) {}
    default void visit(LineComment node) {}

    default void endVisit(BooleanLiteral node) {}
    default void endVisit(CharacterLiteral node) {}
    default void endVisit(NullLiteral node) {}
    default void endVisit(NumberLiteral node) {}
    default void endVisit(StringLiteral node) {}
    default void endVisit(BlockComment node) {}
    default void endVisit(LineComment node) {}
}

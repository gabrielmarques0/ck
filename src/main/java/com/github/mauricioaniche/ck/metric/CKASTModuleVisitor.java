package com.github.mauricioaniche.ck.metric;

import org.eclipse.jdt.core.dom.*;

public interface CKASTModuleVisitor {
    default void visit(ExportsDirective node) {}
    default void visit(OpensDirective node) {}
    default void visit(ProvidesDirective node) {}
    default void visit(RequiresDirective node) {}
    default void visit(UsesDirective node) {}
    default void visit(ModuleModifier node) {}

    default void endVisit(ExportsDirective node) {}
    default void endVisit(OpensDirective node) {}
    default void endVisit(ProvidesDirective node) {}
    default void endVisit(RequiresDirective node) {}
    default void endVisit(UsesDirective node) {}
    default void endVisit(ModuleModifier node) {}
}

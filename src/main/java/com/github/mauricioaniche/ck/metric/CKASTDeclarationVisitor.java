public interface CKASTDeclarationVisitor {
    default void visit(AnnotationTypeDeclaration node) {}
    default void visit(AnnotationTypeMemberDeclaration node) {}
    default void visit(AnonymousClassDeclaration node) {}
    default void visit(EnumConstantDeclaration node) {}
    default void visit(EnumDeclaration node) {}
    default void visit(FieldDeclaration node) {}
    default void visit(MethodDeclaration node) {}
    default void visit(ModuleDeclaration node) {}
    default void visit(PackageDeclaration node) {}
    default void visit(TypeDeclaration node) {}
    default void visit(TypeDeclarationStatement node) {}

    default void endVisit(AnnotationTypeDeclaration node) {}
    default void endVisit(AnnotationTypeMemberDeclaration node) {}
    default void endVisit(AnonymousClassDeclaration node) {}
    default void endVisit(EnumConstantDeclaration node) {}
    default void endVisit(EnumDeclaration node) {}
    default void endVisit(FieldDeclaration node) {}
    default void endVisit(MethodDeclaration node) {}
    default void endVisit(ModuleDeclaration node) {}
    default void endVisit(PackageDeclaration node) {}
    default void endVisit(TypeDeclaration node) {}
    default void endVisit(TypeDeclarationStatement node) {}
}

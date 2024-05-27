public interface CKASTTypeVisitor {
    default void visit(ArrayType node) {}
    default void visit(IntersectionType node) {}
    default void visit(ParameterizedType node) {}
    default void visit(PrimitiveType node) {}
    default void visit(QualifiedType node) {}
    default void visit(SimpleType node) {}
    default void visit(UnionType node) {}
    default void visit(WildcardType node) {}

    default void endVisit(ArrayType node) {}
    default void endVisit(IntersectionType node) {}
    default void endVisit(ParameterizedType node) {}
    default void endVisit(PrimitiveType node) {}
    default void endVisit(QualifiedType node) {}
    default void endVisit(SimpleType node) {}
    default void endVisit(UnionType node) {}
    default void endVisit(WildcardType node) {}
}

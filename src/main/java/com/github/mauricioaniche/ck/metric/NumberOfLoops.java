package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import org.eclipse.jdt.core.dom.*;

public class NumberOfLoops implements CKASTVisitor, ClassLevelMetric, MethodLevelMetric {

	private int qty = 0;

	@Override
	public void visit(EnhancedForStatement node) {
		qty++;
	}

	@Override
	public void visit(DoStatement node) {
		qty++;
	}

	@Override
	public void visit(WhileStatement node) {
		qty++;
	}

	@Override
	public void visit(ForStatement node) {
		qty++;
	}

	@Override
	public void setResult(CKMethodResult result) {
		result.setLoopQty(qty);

	}

	@Override
	public void setResult(CKClassResult result) {
		result.setLoopQty(qty);
	}
}

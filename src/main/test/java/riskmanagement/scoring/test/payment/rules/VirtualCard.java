package riskmanagement.scoring.test.payment.rules;

import riskmanagement.scoring.api.Rule;
import riskmanagement.scoring.api.RuleResult;
import riskmanagement.scoring.test.payment.context.Transaction;

/**
 *
 * @author mathieu
 *
 */
public class VirtualCard extends Rule<Transaction> {

	/**
	 *
	 * @param weight
	 */
	public VirtualCard(double weight) {
		super(VirtualCard.class.getSimpleName(), weight);
	}

	@Override
	public boolean isApplicable(Transaction trx) {
		return trx != null && trx.getCard() != null;
	}

	@Override
	public RuleResult evaluate(Transaction trx) {
		boolean hit = trx.getCard().isVirtual();
		double score = hit ? (double)this.getWeight() : 0.0;

		return new RuleResult(this.getName(), score, hit);
	}

}

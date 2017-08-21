package riskmanagement.scoring.test.payment.rules;

import riskmanagement.common.RuleSettings;
import riskmanagement.scoring.Rule;
import riskmanagement.scoring.RuleResult;
import riskmanagement.scoring.test.payment.context.Transaction;

/**
 *
 * @author Matschieu
 *
 */
public class VirtualCard extends Rule {

	/**
	 *
	 */
	public VirtualCard() {
		super(VirtualCard.class.getSimpleName());
	}

	@Override
	public <C> boolean isApplicable(C context, RuleSettings ruleSettings) {
		if (context instanceof Transaction) {
			Transaction trx = (Transaction)context;
			return trx.getCard() != null;
		}
		return false;
	}

	@Override
	public <C> RuleResult evaluate(C context, RuleSettings ruleSettings) {
		Transaction trx = (Transaction)context;
		boolean hit = trx.getCard().isVirtual();
		double score = hit ? ruleSettings.getWeight() : 0.0;

		return new RuleResult(this.getName(), score, hit);
	}

}

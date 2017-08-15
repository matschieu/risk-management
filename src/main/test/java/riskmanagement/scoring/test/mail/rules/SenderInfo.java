package riskmanagement.scoring.test.mail.rules;

import riskmanagement.scoring.api.Rule;
import riskmanagement.scoring.api.RuleResult;
import riskmanagement.scoring.test.mail.context.Mail;

/**
 *
 * @author mathieu
 *
 */
public class SenderInfo extends Rule<Mail> {

	/**
	 *
	 * @param weight
	 */
	public SenderInfo(double weight) {
		super(SenderInfo.class.getSimpleName(), weight);
	}

	@Override
	public boolean isApplicable(Mail context) {
		return context != null;
	}

	@Override
	public RuleResult evaluate(Mail context) {
		// TODO Auto-generated method stub
		return null;
	}

}

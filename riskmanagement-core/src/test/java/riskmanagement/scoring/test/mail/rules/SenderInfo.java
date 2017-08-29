package riskmanagement.scoring.test.mail.rules;

import java.net.InetAddress;

import riskmanagement.common.RuleSettings;
import riskmanagement.common.Stage;
import riskmanagement.scoring.Rule;
import riskmanagement.scoring.RuleResult;
import riskmanagement.scoring.test.mail.context.Mail;

/**
 *
 * @author Matschieu
 *
 */
public class SenderInfo extends Rule {

	/**
	 *
	 */
	public SenderInfo() {
		super(SenderInfo.class.getSimpleName());
	}

	@Override
	public <C> boolean isApplicable(C context, Stage stage, RuleSettings ruleSettings) {
		if (context instanceof Mail) {
			Mail mail = (Mail)context;
			return mail.getFrom() != null && !mail.getFrom().isEmpty();
		}
		return false;
	}

	@Override
	public <C> RuleResult evaluate(C context, Stage stage, RuleSettings ruleSettings) {
		RuleResult ruleResult = null;
		boolean hit = true;
		Mail mail = (Mail)context;

		try {
			final String from = mail.getFrom();

			if (from.contains("@") && !from.endsWith("@")) {
				InetAddress.getByName(mail.getFrom().substring(mail.getFrom().indexOf("@") + 1));
				hit = false;
			}
		} catch(Exception e) {
		} finally {
			if (hit) {
				ruleResult = new RuleResult(this.getName(), ruleSettings.getWeight(), true);
			} else {
				ruleResult = new RuleResult(this.getName(), 0, false);
			}

		}

		return ruleResult;
	}

}

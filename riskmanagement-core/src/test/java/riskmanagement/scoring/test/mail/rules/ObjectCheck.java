package riskmanagement.scoring.test.mail.rules;

import java.util.Arrays;
import java.util.List;

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
public class ObjectCheck extends Rule {

	private static final List<String> wordBlackList = Arrays.asList("VIAGRA", "SEX");

	/**
	 *
	 */
	public ObjectCheck() {
		super(ObjectCheck.class.getSimpleName());
	}

	@Override
	public <C> boolean isApplicable(C context, Stage stage, RuleSettings ruleSettings) {
		if (context instanceof Mail) {
			Mail mail = (Mail)context;
			return mail.getObject() != null && !mail.getObject().isEmpty();
		}
		return false;
	}

	@Override
	public <C> RuleResult evaluate(C context, Stage stage, RuleSettings ruleSettings) {
		RuleResult ruleResult = null;
		Mail mail = (Mail)context;

		if (wordBlackList.stream().anyMatch(word -> mail.getObject().toUpperCase().contains(word))) {
			ruleResult = new RuleResult(this.getName(), ruleSettings.getWeight(), true);
		} else {
			ruleResult = new RuleResult(this.getName(), 0, false);
		}

		return ruleResult;
	}

}

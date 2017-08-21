package riskmanagement.scoring;

import riskmanagement.common.RuleSettings;

/**
 * @author Matschieu
 *
 */
class RuleWrapper {

	private Rule rule;

	private RuleSettings ruleSettings;

	/**
	 * @return Rule
	 */
	Rule getRule() {
		return rule;
	}

	/**
	 * @param rule (Rule)
	 */
	void setRule(Rule rule) {
		this.rule = rule;
	}

	/**
	 * @return RuleSettings
	 */
	RuleSettings getRuleSettings() {
		return ruleSettings;
	}

	/**
	 * @param ruleSettings (RuleSettings)
	 */
	void setRuleSettings(RuleSettings ruleSettings) {
		this.ruleSettings = ruleSettings;
	}

}

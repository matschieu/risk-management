package riskmanagement.scoring;

import riskmanagement.common.RuleSettings;

/**
 *
 * A class to associate a rule to the settings.
 *
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
	 * @param rule
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
	 * @param ruleSettings
	 */
	void setRuleSettings(RuleSettings ruleSettings) {
		this.ruleSettings = ruleSettings;
	}

}

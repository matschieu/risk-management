package riskmanagement.scoring;

import riskmanagement.common.RuleSettings;

/**
 *
 * @author mathieu
 *
 */
public abstract class Rule {

	private final String name;

	/**
	 *
	 * @param name
	 */
	public Rule(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "Rule: " + this.name;
	}

	/**
	 *
	 * @param context
	 * @param ruleSettings
	 * @return boolean
	 */
	public abstract <C> boolean isApplicable(C context, RuleSettings ruleSettings);

	/**
	 *
	 * @param context
	 * @param ruleSettings
	 * @return RuleResult
	 */
	public abstract <C> RuleResult evaluate(C context, RuleSettings ruleSettings);

}

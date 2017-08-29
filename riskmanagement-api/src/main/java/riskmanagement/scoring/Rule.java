package riskmanagement.scoring;

import riskmanagement.common.RuleSettings;
import riskmanagement.common.Stage;

/**
 *
 * A rule is a logic used to evaluate a part of the risk of a context.
 * It is possible to create many rules as necessary based on different business context.
 *
 * @author Matschieu
 *
 */
public abstract class Rule {

	private final String name;

	/**
	 *
	 * @param name: the name to identify this rule (must be unique)
	 */
	public Rule(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return String: the rule name
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
	 * Returns whether this rule is applicable in the current context and stage.
	 *
	 * @param context: the context to evaluate the risk
	 * @param stage: the stage when risk the evaluation is done
	 * @param ruleSettings: the settings of this rule
	 * @return boolean: true if this rule is applicable
	 */
	public abstract <C> boolean isApplicable(C context, Stage stage, RuleSettings ruleSettings);

	/**
	 *
	 * Evaluates this rule in the current context and stage.
	 *
	 * @param context: the context to evaluate the risk
	 * @param stage: the stage when risk the evaluation is done
	 * @param ruleSettings: the settings of this rule
	 * @return RuleResult: the result of the risk evaluation
	 */
	public abstract <C> RuleResult evaluate(C context, Stage stage, RuleSettings ruleSettings);

}

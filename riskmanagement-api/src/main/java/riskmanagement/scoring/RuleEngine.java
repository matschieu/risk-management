package riskmanagement.scoring;

import java.util.List;

import riskmanagement.common.RuleSettings;

/**
 *
 * @author mathieu
 *
 */
public interface RuleEngine {

	/**
	 *
	 * @return DecisionHelper
	 */
	DecisionHelper getDecisionHelper();

	/**
	 *
	 * @param action
	 */
	void registerAction(Action action);

	/**
	 *
	 * @param action
	 */
	void removeAction(Action action);

	/**
	 *
	 * @return List<Action<C>>
	 */
	List<Action> getActions();

	/**
	 *
	 * @param rule
	 */
	void registerRule(Rule rule, RuleSettings ruleSettings);

	/**
	 *
	 * @param rule
	 */
	void removeRule(Rule rule);

	/**
	 *
	 * @return List<Rule<C>>
	 */
	List<Rule> getRules();

	/**
	 *
	 * @param context
	 * @return Result
	 */
	<C> Result process(C context);

}

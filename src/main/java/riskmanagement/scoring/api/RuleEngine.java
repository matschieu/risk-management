package riskmanagement.scoring.api;

import java.util.List;

/**
 *
 * @author mathieu
 *
 */
public interface RuleEngine<C> {

	/**
	 *
	 * @return DecisionHelper
	 */
	DecisionHelper getDecisionHelper();

	/**
	 *
	 * @param action
	 */
	void registerAction(Action<C> action);

	/**
	 *
	 * @param action
	 */
	void removeAction(Action<C> action);

	/**
	 *
	 * @return List<Action<C>>
	 */
	List<Action<C>> getActions();

	/**
	 *
	 * @param rule
	 */
	void registerRule(Rule<C> rule);

	/**
	 *
	 * @param rule
	 */
	void removeRule(Rule<C> rule);

	/**
	 *
	 * @return List<Rule<C>>
	 */
	List<Rule<C>> getRules();

	/**
	 *
	 * @param context
	 * @return Result
	 */
	Result process(C context);

}

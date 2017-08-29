package riskmanagement.scoring;

import java.util.List;

import riskmanagement.common.RuleSettings;
import riskmanagement.common.Stage;

/**
 *
 * The rule engine is the core of the risk evaluation tool. It applies the rules on the context to evaluate the risk.
 * The risk is represented by a score and a color in the result calculated using a decision helper.
 *
 * @author Matschieu
 *
 */
public interface RuleEngine {

	/**
	 *
	 * @return DecisionHelper: the decision helper used to evaluate the risk
	 */
	DecisionHelper getDecisionHelper();

	/**
	 *
	 * Registers one action to apply
	 *
	 * @param action: the action to register
	 */
	void registerAction(Action action);

	/**
	 *
	 * Removes a action registered before
	 *
	 * @param action: the action to remove
	 */
	void removeAction(Action action);

	/**
	 *
	 * Returns the list of registered actions to apply
	 *
	 * @return List<Action<C>>: the list of registered actions
	 */
	List<Action> getActions();

	/**
	 *
	 * Registers one rule to apply during the risk evaluation
	 *
	 * @param rule: the rule to register
	 * @param ruleSettings: the settings of the rule to register
	 */
	void registerRule(Rule rule, RuleSettings ruleSettings);

	/**
	 *
	 * Removes a rule registered before
	 *
	 * @param rule: the rule to remove
	 */
	void removeRule(Rule rule);

	/**
	 *
	 * Returns the list of registered rules to apply during the risk evaluation
	 *
	 * @return List<Rule<C>>: the list of registered rules
	 */
	List<Rule> getRules();

	/**
	 *
	 * Evaluates the risk of the context given in parameter for a specific stage.
	 *
	 * @param context: the context to evaluate the risk
	 * @param stage: the stage when risk the evaluation is done
	 * @return Result: the result of the risk evaluation
	 */
	<C> Result process(C context, Stage stage);

}

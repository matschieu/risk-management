package riskmanagement.scoring;

import java.util.List;

import riskmanagement.common.Stage;

/**
 *
 * An action is something to do at a specific stage in the processing.
 * An action can be extended as wanted.
 *
 * @author Matschieu
 *
 */
public abstract class Action {

	private String name;

	/**
	 *
	 * @param name: the name to identify this action
	 */
	public Action(String name) {
		this.name = name;
	}

	/**
	 *
	 * Returns the name of this action
	 *
	 * @return String: the name of this action
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Action: " + name;
	}

	/**
	 *
	 * Returns whether this action is applicable before the risk evaluation processing
	 *
	 * @param context
	 * @param stage
	 * @return boolean
	 */
	public abstract <C> boolean isPreProcessingApplicable(C context, Stage stage);

	/**
	 *
	 * Returns whether this action is applicable after the risk evaluation processing
	 *
	 * @param context
	 * @param stage
	 * @return boolean
	 */
	public abstract <C> boolean isPostProcessingApplicable(C context, Stage stage);

	/**
	 *
	 * Action to execute before the risk evaluation processing
	 *
	 * @param rules
	 * @param context
	 * @param stage
	 */
	public abstract <C> void executePreProcessing(List<Rule> rules, C context, Stage stage);

	/**
	 *
	 * Action to execute after the risk evaluation processing
	 *
	 * @param rules
	 * @param context
	 * @param stage
	 * @param result
	 */
	public abstract <C> void executePostProcessing(List<Rule> rules, C context, Stage stage, Result result);

}

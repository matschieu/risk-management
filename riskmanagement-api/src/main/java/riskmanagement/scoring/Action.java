package riskmanagement.scoring;

import java.util.List;

/**
 *
 * @author Matschieu
 *
 */
public abstract class Action {

	private String name;

	/**
	 *
	 * @param name
	 */
	public Action(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return String
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
	 * @param rules
	 * @param context
	 */
	public abstract <C> void executePreProcessing(List<Rule> rules, C context);

	/**
	 *
	 * @param rules
	 * @param context
	 * @param result
	 */
	public abstract <C> void executePostProcessing(List<Rule> rules, C context, Result result);

}

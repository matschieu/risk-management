package riskmanagement.scoring.api;

import java.util.List;

/**
 *
 * @author mathieu
 *
 */
public abstract class Action<C> {

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
	public abstract void executePreProcessing(List<Rule<C>> rules, C context);

	/**
	 *
	 * @param rules
	 * @param context
	 * @param result
	 */
	public abstract void executePostProcessing(List<Rule<C>> rules, C context, Result result);

}

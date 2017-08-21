package riskmanagement.common;

import java.util.Map;


/**
 * @author Matschieu
 *
 */
public class RuleSettings extends Settings {

	private double weight;

	/**
	 *
	 */
	public RuleSettings() { }

	/**
	 *
	 * @param values
	 */
	public RuleSettings(double weight) {
		super();
		this.weight = weight;
	}

	/**
	 *
	 * @param values
	 */
	public RuleSettings(double weight, Map<String, Object> values) {
		super(values);
		this.weight = weight;
	}

	/**
	 * @return double
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight (double)
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

}

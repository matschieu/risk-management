package riskmanagement.common;

import java.util.Map;


/**
 *
 * The settings represents a list a key/value couples representing the configuration of a rule.
 *
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
	 * @param weight: the weight of the rule
	 */
	public RuleSettings(double weight) {
		super();
		this.weight = weight;
	}

	/**
	 *
	 * @param weight: the weight of the rule
	 * @param values: the key/value couples
	 */
	public RuleSettings(double weight, Map<String, Object> values) {
		super(values);
		this.weight = weight;
	}

	/**
	 * @return double: the weight of the rule
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight: the weight of the rule
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

}

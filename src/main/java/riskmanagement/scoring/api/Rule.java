package riskmanagement.scoring.api;


/**
 *
 * @author mathieu
 *
 */
public abstract class Rule<C> {

	private final double weight;

	private final String name;

	/**
	 *
	 * @param name
	 * @param weight
	 */
	public Rule(String name, double weight) {
		this.weight = weight;
		this.name = name;
	}

	/**
	 *
	 * @return double
	 */
	public double getWeight() {
		return this.weight;
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
	 * @return boolean
	 */
	public abstract boolean isApplicable(C context);

	/**
	 *
	 * @param context
	 * @return RuleResult
	 */
	public abstract RuleResult evaluate(C context);

}

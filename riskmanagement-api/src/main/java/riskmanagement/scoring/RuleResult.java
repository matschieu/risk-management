package riskmanagement.scoring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 *
 * The result produced by a risk evaluation of a specific context
 *
 * @author Matschieu
 *
 */
public class RuleResult {

	private String ruleName;

	private boolean applicable;

	private double score;

	private Boolean hit;

	/**
	 *
	 * @param ruleName: the name of the rule which produced this result
	 */
	public RuleResult(String ruleName) {
		this.ruleName = ruleName;
		this.score = 0;
		this.hit = false;
		this.applicable = false;
	}

	/**
	 *
	 * @param ruleName: the name of the rule which produced this result
	 * @param score: the score of this single rule
	 * @param hit: did the rule return a hit?
	 */
	public RuleResult(String ruleIdentifier, double score, boolean hit) {
		this.ruleName = ruleIdentifier;
		this.score = score;
		this.hit = hit;
		this.applicable = true;
	}


	/**
	 *
	 * @return String: the name of the rule which produced this result
	 */
	public String getRuleName() {
		return this.ruleName;
	}

	/**
	 *
	 * @return double: the score of this single rule
	 */
	public double getScore() {
		return this.score;
	}

	/**
	 *
	 * @return boolean: true if this rule returns a hit
	 */
	public boolean hasHit() {
		return this.hit;
	}

	/**
	 *
	 * @return boolean: true if this rule was applicable to the context
	 */
	public boolean isApplicable() {
		return this.applicable;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

}

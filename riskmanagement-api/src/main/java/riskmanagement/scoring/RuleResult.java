package riskmanagement.scoring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 *
 * @author mathieu
 *
 */
public class RuleResult {

	private String ruleIdentifier;

	private boolean applicable;

	private double score;

	private Boolean hit;

	/**
	 *
	 * @param ruleIdentifier
	 */
	public RuleResult(String ruleIdentifier) {
		this.ruleIdentifier = ruleIdentifier;
		this.score = 0;
		this.hit = false;
		this.applicable = false;
	}

	/**
	 *
	 * @param ruleIdentifier
	 * @param score
	 * @param hit
	 */
	public RuleResult(String ruleIdentifier, double score, boolean hit) {
		this.ruleIdentifier = ruleIdentifier;
		this.score = score;
		this.hit = hit;
		this.applicable = true;
	}


	/**
	 *
	 * @return String
	 */
	public String getRuleName() {
		return this.ruleIdentifier;
	}

	/**
	 *
	 * @return double
	 */
	public double getScore() {
		return this.score;
	}

	/**
	 *
	 * @return boolean
	 */
	public boolean hasHit() {
		return this.hit;
	}

	/**
	 *
	 * @return boolean
	 */
	public boolean isApplicable() {
		return this.applicable;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

}

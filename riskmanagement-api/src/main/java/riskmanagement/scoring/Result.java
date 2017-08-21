package riskmanagement.scoring;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Matschieu
 *
 */
public class Result {

	private double score;

	private Color color;

	private List<RuleResult> ruleResults;

	private double maxScore;

	private double minScore;

	private int hitCounter;

	private int notApplicableCounter;

	/**
	 *
	 * @param score
	 * @param color
	 * @param ruleResults
	 */
	public Result(double score, Color color, List<RuleResult> ruleResults) {
		this.score = score;
		this.color = color;
		this.ruleResults = ruleResults;

		this.maxScore = 0;
		this.minScore = 0;
		this.hitCounter = 0;
		this.notApplicableCounter = 0;

		if (ruleResults != null) {
			ruleResults.forEach(rulesResult -> {
				if (rulesResult.getScore() > maxScore) {
					maxScore = rulesResult.getScore();
				}
				if (rulesResult.getScore() < minScore) {
					minScore = rulesResult.getScore();
				}
				if (rulesResult.hasHit()) {
					this.hitCounter++;
				}
				if (!rulesResult.isApplicable()) {
					this.notApplicableCounter++;
				}
			});
		}
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
	 * @return Color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return List<RuleResult>
	 */
	public List<RuleResult> getRuleResults() {
		return ruleResults;
	}

	/**
	 * @return double
	 */
	public double getMaxScore() {
		return maxScore;
	}

	/**
	 * @return double
	 */
	public double getMinScore() {
		return minScore;
	}

	/**
	 * @return int
	 */
	public int getHitCounter() {
		return hitCounter;
	}

	/**
	 * @return int
	 */
	public int getNotApplicableCounter() {
		return notApplicableCounter;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

}

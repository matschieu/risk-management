package riskmanagement.scoring;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * The result of a risk evaluation.
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

	private int preProcessingActions;

	private int postProcessingActions;

	/**
	 *
	 * @param score: the final score of the risk evaluation
	 * @param color: the color based on the final score
	 * @param ruleResults: the list of applied rules (including the not applicable)
	 * @param preProcessingActions: the number of actions applied before the risk evaluation
	 * @param postProcessingActions: the number of actions applied after the risk evaluation
	 */
	public Result(double score, Color color, List<RuleResult> ruleResults, int preProcessingActions, int postProcessingActions) {
		this.score = score;
		this.color = color;
		this.ruleResults = ruleResults;

		this.preProcessingActions = preProcessingActions;
		this.postProcessingActions = postProcessingActions;

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
	 * @return double: the final score of the risk evaluation
	 */
	public double getScore() {
		return this.score;
	}

	/**
	 *
	 * @return Color: the color based on the final score
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
	 * @return double: the highest score for a single rule
	 */
	public double getMaxScore() {
		return maxScore;
	}

	/**
	 * @return double: the lowest score for a single rule
	 */
	public double getMinScore() {
		return minScore;
	}

	/**
	 * @return int: the number of hits returned by the rules
	 */
	public int getHitCounter() {
		return hitCounter;
	}

	/**
	 * @return int: the number of rule that are not applicable to the context
	 */
	public int getNotApplicableCounter() {
		return notApplicableCounter;
	}

	/**
	 * @return int: the number of actions applied before the risk evaluation
	 */
	public int getPreProcessingActions() {
		return preProcessingActions;
	}

	/**
	 * @return int: the number of actions applied after the risk evaluation
	 */
	public int getPostProcessingActions() {
		return postProcessingActions;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

}

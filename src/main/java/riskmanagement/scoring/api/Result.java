package riskmanagement.scoring.api;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author mathieu
 *
 */
public class Result {

	private double score;

	private Color color;

	private List<RuleResult> rulesResults;

	/**
	 *
	 * @param score
	 * @param color
	 * @param rulesResults
	 */
	public Result(double score, Color color, List<RuleResult> rulesResults) {
		this.score = score;
		this.color = color;
		this.rulesResults = rulesResults;
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
	 *
	 * @return List<RuleResult>
	 */
	public List<RuleResult> getRuleResults() {
		return this.rulesResults;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

}

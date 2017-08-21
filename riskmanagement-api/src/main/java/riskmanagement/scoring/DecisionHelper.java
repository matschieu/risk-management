package riskmanagement.scoring;

import java.util.List;

/**
 *
 * @author Matschieu
 *
 */
public interface DecisionHelper {

	/**
	 *
	 * @return double
	 */
	double getMinThreshold();

	/**
	 *
	 * @return double
	 */
	double getMaxThreshold();

	/**
	 *
	 * @param ruleResults
	 * @return double
	 */
	double calculateFinalScore(List<RuleResult> ruleResults);

	/**
	 *
	 * @param score
	 * @return Color
	 */
	Color calculateColor(double score);

}

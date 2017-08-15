package riskmanagement.scoring.api;

import java.util.List;

/**
 *
 * @author mathieu
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

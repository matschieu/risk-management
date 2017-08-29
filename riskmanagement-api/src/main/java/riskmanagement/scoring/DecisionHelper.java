package riskmanagement.scoring;

import java.util.List;

/**
 *
 * The decision helper is a tool used to calculate the final score of a risk evaluation
 * It is possible to define a new decision helper habing a different behaviour than the default one.
 *
 * @author Matschieu
 *
 */
public interface DecisionHelper {

	/**
	 *
	 * @return double: the minimum threshold
	 */
	double getMinThreshold();

	/**
	 *
	 * @return double: the minimum threshold
	 */
	double getMaxThreshold();

	/**
	 *
	 * Calculates the final score of the risk evaluation of the context according to the result of each rule applied.
	 * The list of rules contains a result for the not applicable rules.
	 *
	 * @param ruleResults: the list of rules applied
	 * @return double: the final score
	 */
	double calculateFinalScore(List<RuleResult> ruleResults);

	/**
	 *
	 * Calculates the color representing the risk based on a final score.
	 *
	 * @param score: the final score of the risk evaluation
	 * @return Color: the color based on the score
	 */
	Color calculateColor(double score);

}

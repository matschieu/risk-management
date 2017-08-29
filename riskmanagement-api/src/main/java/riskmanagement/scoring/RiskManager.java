package riskmanagement.scoring;

import javax.ejb.Local;

import riskmanagement.common.Stage;

/**
 *
 * A risk manager is a high level service used to evaluate the risk of a context.
 *
 * @author Matschieu
 *
 */
@Local
public interface RiskManager {

	/**
	 *
	 * This method retrieves the active profile to applied on the context and then
	 * evaluates the risk of a context for a specific account ID and a specific stage.
	 *
	 * @param accountId: the account ID
	 * @param context: the context to evaluate
	 * @param stage: the stage when risk the evaluation is done
	 * @return Result: the result of the risk evaluation
	 */
	<T> Result process(String accountId, T context, Stage stage);

}

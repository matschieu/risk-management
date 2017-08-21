package riskmanagement.scoring;

import javax.ejb.Local;

/**
 * @author mathieu
 *
 */
@Local
public interface RiskManager {

	/**
	 *
	 * @param accountId
	 * @param context
	 * @return <T>
	 */
	<T> Result process(String accountId, T context);

}

/**
 *
 */
package riskmanagement.scoring;

import java.util.ServiceLoader;



/**
 * @author mathieu
 *
 */
public class RuleFactory {

	private static final Object LOCK = new Object();

	private static ServiceLoader<Rule> ruleLoader;

	private static RuleFactory instance;

	/**
	 *
	 * @return RuleRegistry
	 */
	public static RuleFactory getInstance() {
		if (instance == null) {
			synchronized (LOCK) {
				if (instance == null) {
					instance = new RuleFactory();
				}
			}
		}

		return instance;
	}

	/**
	 *
	 */
	private RuleFactory() {
		ruleLoader = ServiceLoader.load(Rule.class);
	}

	/**
	 *
	 * @param ruleName
	 * @return Rule
	 * @throws IllegalArgumentException
	 */
	public Rule getRule(String ruleName) throws IllegalArgumentException {
		Rule rule = null;

		if (ruleName != null && !ruleName.isEmpty()) {
			for(Rule tmpRule : ruleLoader) {
				if (ruleName.equals(tmpRule.getName())) {
					rule = tmpRule;
					break;
				}
			}
		}

		if (rule == null) {
			throw new IllegalArgumentException(String.format("Rule with name %s not found", ruleName));
		}

		return rule;
	}

}

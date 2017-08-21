/**
 *
 */
package riskmanagement.scoring.test;

import org.junit.Assert;
import org.junit.Test;

import riskmanagement.scoring.Rule;
import riskmanagement.scoring.RuleFactory;
import riskmanagement.scoring.test.payment.rules.CommercialCard;

/**
 * @author Matschieu
 *
 */
public class RuleFactoryTest {

	@Test
	public void testRuleFactory() {
		Rule rule = RuleFactory.getInstance().getRule(CommercialCard.class.getSimpleName());

		Assert.assertNotNull(rule);
		Assert.assertEquals(CommercialCard.class.getSimpleName(), rule.getName());
	}

}

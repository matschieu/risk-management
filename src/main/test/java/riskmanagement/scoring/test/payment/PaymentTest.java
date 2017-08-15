package riskmanagement.scoring.test.payment;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import riskmanagement.scoring.api.Action;
import riskmanagement.scoring.api.Color;
import riskmanagement.scoring.api.Result;
import riskmanagement.scoring.api.Rule;
import riskmanagement.scoring.api.RuleEngine;
import riskmanagement.scoring.api.RuleResult;
import riskmanagement.scoring.impl.DecisionHelperImpl;
import riskmanagement.scoring.impl.RuleEngineImpl;
import riskmanagement.scoring.test.payment.context.Card;
import riskmanagement.scoring.test.payment.context.Transaction;
import riskmanagement.scoring.test.payment.rules.CommercialCard;
import riskmanagement.scoring.test.payment.rules.VirtualCard;

/**
 *
 * @author mathieu
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class PaymentTest {

	private static RuleEngine<Transaction> ruleEngine;
	private static Transaction transaction;

	@BeforeClass
	public static void init() throws Exception {
		ruleEngine = new RuleEngineImpl<Transaction>(new DecisionHelperImpl(-2, -1));
		ruleEngine.registerAction(new Action<Transaction>("Action1") {
			@Override
			public void executePreProcessing(List<Rule<Transaction>> rules, Transaction context) {
				System.out.println("Pre-processing 1");

			}

			@Override
			public void executePostProcessing(List<Rule<Transaction>> rules, Transaction context, Result result) {
				System.out.println("Post-processing 1");

			}
		});
		ruleEngine.registerAction(new Action<Transaction>("Action2") {
			@Override
			public void executePreProcessing(List<Rule<Transaction>> rules, Transaction context) {
				System.out.println("Pre-processing 2");

			}

			@Override
			public void executePostProcessing(List<Rule<Transaction>> rules, Transaction context, Result result) {
				System.out.println("Post-processing 2");

				System.out.println("Score = " + result.getScore());
				System.out.println("Color = " + result.getColor());
				for(RuleResult ruleResult : result.getRuleResults()) {
					System.out.println("* " + ruleResult.getRuleName() + " = " + ruleResult.getScore());
				}
			}
		});

		transaction = new Transaction(new Card());

		ruleEngine.registerRule(new VirtualCard(4.0));
		ruleEngine.registerRule(new CommercialCard(3.0));
	}

	@Test
	public void testBadConfig() throws Exception {
		try {
			new RuleEngineImpl<Transaction>(null);
			Assert.fail();
		} catch(Exception e) { }

		try {
			new RuleEngineImpl<Transaction>(new DecisionHelperImpl(3, 1));
			Assert.fail();
		} catch(Exception e) { }
	}

	@Test
	public void testNoRule() throws Exception {
		Result result = new RuleEngineImpl<Transaction>(new DecisionHelperImpl(-1, -1)).process(transaction);

		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getScore());
		Assert.assertEquals(0, result.getScore(), 0);
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.GREEN, result.getColor());
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(0, result.getRuleResults().size());
	}

	@Test
	public void testOK() {
		transaction.getCard().setVirtual(false);
		transaction.getCard().setCommercial(false);;

		Result result = ruleEngine.process(transaction);

		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getScore());
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.GREEN, result.getColor());
		Assert.assertEquals(0, result.getScore(), 0);
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

	@Test
	public void testNOK() {
		transaction.getCard().setVirtual(true);
		transaction.getCard().setCommercial(true);;

		Result result = ruleEngine.process(transaction);

		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getScore());
		Assert.assertEquals(3.5, result.getScore(), 0);
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.GREEN, result.getColor());
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

	@Test
	public void testRuleNotApplicable() {
		Result result = ruleEngine.process(new Transaction());

		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.getScore(), 0);
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.GREEN, result.getColor());
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

	@Test
	public void testColorRed() throws Exception {
		ruleEngine = new RuleEngineImpl<Transaction>(new DecisionHelperImpl(100, 100));
		ruleEngine.registerRule(new VirtualCard(4.0));
		ruleEngine.registerRule(new CommercialCard(3.0));

		Result result = ruleEngine.process(new Transaction());

		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.getScore(), 0);
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.RED, result.getColor());
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

	@Test
	public void testColorOrange() throws Exception {
		ruleEngine = new RuleEngineImpl<Transaction>(new DecisionHelperImpl(0, 100));
		ruleEngine.registerRule(new VirtualCard(4.0));
		ruleEngine.registerRule(new CommercialCard(3.0));

		Result result = ruleEngine.process(new Transaction());

		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.getScore(), 0);
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.ORANGE, result.getColor());
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

	@Test
	public void testColorGreen() throws Exception {
		ruleEngine = new RuleEngineImpl<Transaction>(new DecisionHelperImpl(-2, -1));
		ruleEngine.registerRule(new VirtualCard(4.0));
		ruleEngine.registerRule(new CommercialCard(3.0));

		Result result = ruleEngine.process(new Transaction());

		System.out.println(result);

		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.getScore(), 0);
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.GREEN, result.getColor());
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

}

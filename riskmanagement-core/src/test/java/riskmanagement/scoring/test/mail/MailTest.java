package riskmanagement.scoring.test.mail;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import riskmanagement.common.RuleSettings;
import riskmanagement.scoring.Action;
import riskmanagement.scoring.Color;
import riskmanagement.scoring.DefaultDecisionHelper;
import riskmanagement.scoring.DefaultRuleEngine;
import riskmanagement.scoring.Result;
import riskmanagement.scoring.Rule;
import riskmanagement.scoring.RuleEngine;
import riskmanagement.scoring.RuleResult;
import riskmanagement.scoring.test.mail.context.Mail;
import riskmanagement.scoring.test.mail.rules.ObjectCheck;
import riskmanagement.scoring.test.mail.rules.SenderInfo;

/**
 *
 * @author mathieu
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class MailTest {

	private static RuleEngine ruleEngine;
	private static Mail mail;

	@BeforeClass
	public static void init() throws Exception {
		ruleEngine = new DefaultRuleEngine(new DefaultDecisionHelper(-2, -1));
		ruleEngine.registerAction(new Action("Action1") {
			@Override
			public <C> void executePreProcessing(List<Rule> rules, C context) {
				System.out.println("Pre-processing 1");

			}

			@Override
			public <C> void executePostProcessing(List<Rule> rules, C context, Result result) {
				System.out.println("Post-processing 2");

				System.out.println("Score = " + result.getScore());
				System.out.println("Color = " + result.getColor());
				for(RuleResult ruleResult : result.getRuleResults()) {
					System.out.println("* " + ruleResult.getRuleName() + " = " + ruleResult.getScore());
				}
			}
		});

		mail = new Mail();

		ruleEngine.registerRule(new ObjectCheck(), new RuleSettings(10.0));
		ruleEngine.registerRule(new SenderInfo(), new RuleSettings(10.0));
	}

	@Test
	public void testBadConfig() throws Exception {
		Result result = ruleEngine.process(mail);

		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getScore());
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.GREEN, result.getColor());
		Assert.assertEquals(0, result.getScore(), 0);
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

	@Test
	public void testNoRule() throws Exception {
		Result result = new DefaultRuleEngine(new DefaultDecisionHelper(-1, -1)).process(mail);

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
		mail.setFrom("toto@toto.com");
		mail.setObject("Mail object");

		Result result = ruleEngine.process(mail);

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
		mail.setFrom("toto@a.com");
		mail.setObject("SEX");

		Result result = ruleEngine.process(mail);

		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getScore());
		Assert.assertEquals(10, result.getScore(), 0);
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.GREEN, result.getColor());
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

	@Test
	public void testRuleNotApplicable() {
		Result result = ruleEngine.process(new Mail());

		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.getScore(), 0);
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.GREEN, result.getColor());
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

	@Test
	public void testColorRed() throws Exception {
		ruleEngine = new DefaultRuleEngine(new DefaultDecisionHelper(100, 100));
		ruleEngine.registerRule(new ObjectCheck(), new RuleSettings(10.0));
		ruleEngine.registerRule(new SenderInfo(), new RuleSettings(10.0));

		Result result = ruleEngine.process(new Mail());

		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.getScore(), 0);
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.RED, result.getColor());
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

	@Test
	public void testColorOrange() throws Exception {
		ruleEngine = new DefaultRuleEngine(new DefaultDecisionHelper(0, 100));
		ruleEngine.registerRule(new ObjectCheck(), new RuleSettings(10.0));
		ruleEngine.registerRule(new SenderInfo(), new RuleSettings(10.0));

		Result result = ruleEngine.process(new Mail());

		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.getScore(), 0);
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.ORANGE, result.getColor());
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

	@Test
	public void testColorGreen() throws Exception {
		ruleEngine = new DefaultRuleEngine(new DefaultDecisionHelper(-2, -1));
		ruleEngine.registerRule(new ObjectCheck(), new RuleSettings(10.0));
		ruleEngine.registerRule(new SenderInfo(), new RuleSettings(10.0));

		Result result = ruleEngine.process(new Mail());

		System.out.println(result);

		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.getScore(), 0);
		Assert.assertNotNull(result.getColor());
		Assert.assertEquals(Color.GREEN, result.getColor());
		Assert.assertNotNull(result.getRuleResults());
		Assert.assertEquals(2, result.getRuleResults().size());
	}

}

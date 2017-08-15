package riskmanagement.scoring.test.mail;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import riskmanagement.scoring.api.Action;
import riskmanagement.scoring.api.Result;
import riskmanagement.scoring.api.Rule;
import riskmanagement.scoring.api.RuleEngine;
import riskmanagement.scoring.api.RuleResult;
import riskmanagement.scoring.impl.DecisionHelperImpl;
import riskmanagement.scoring.impl.RuleEngineImpl;
import riskmanagement.scoring.test.mail.context.Mail;
import riskmanagement.scoring.test.mail.rules.HeaderCheck;
import riskmanagement.scoring.test.mail.rules.SenderInfo;

/**
 *
 * @author mathieu
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class MailTest {

	private static RuleEngine<Mail> ruleEngine;
	private static Mail mail;

	@BeforeClass
	public static void init() throws Exception {
		ruleEngine = new RuleEngineImpl<Mail>(new DecisionHelperImpl(-2, -1));
		ruleEngine.registerAction(new Action<Mail>("Action1") {
			@Override
			public void executePreProcessing(List<Rule<Mail>> rules, Mail context) {
				System.out.println("Pre-processing 1");

			}

			@Override
			public void executePostProcessing(List<Rule<Mail>> rules, Mail context, Result result) {
				System.out.println("Post-processing 2");

				System.out.println("Score = " + result.getScore());
				System.out.println("Color = " + result.getColor());
				for(RuleResult ruleResult : result.getRuleResults()) {
					System.out.println("* " + ruleResult.getRuleName() + " = " + ruleResult.getScore());
				}
			}
		});

		mail = new Mail();

		ruleEngine.registerRule(new HeaderCheck(4.0));
		ruleEngine.registerRule(new SenderInfo(3.0));
	}

	@Test
	public void testBadConfig() throws Exception {

	}

}

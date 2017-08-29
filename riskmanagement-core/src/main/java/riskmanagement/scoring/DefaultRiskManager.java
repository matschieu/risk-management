package riskmanagement.scoring;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riskmanagement.admin.Profile;
import riskmanagement.admin.ProfileManager;
import riskmanagement.common.Stage;

/**
 * @author Matschieu
 *
 */
@Stateless(name = "riskmanagement.scoring.DefaultRiskManager")
public class DefaultRiskManager implements RiskManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRiskManager.class);

	@EJB
	private ProfileManager profileManager;

	private RuleFactory ruleFactory = RuleFactory.getInstance();

	@Override
	public <C> Result process(String accountId, C context, Stage stage) {
		final RuleEngine ruleEngine;

		// TODO: find the key
		// Loading a profile containing the rule and their settings
		LOGGER.info("Getting default profile; accountId = {}", accountId);

		Profile profile = profileManager.findActiveProfile(accountId, stage);

		if (profile != null) {
			LOGGER.info("Profile found; accountId = {}, profileName = {}", profile.getAccountId(), profile.getName());

			ruleEngine = new DefaultRuleEngine(new DefaultDecisionHelper(profile.getMinThreshold(), profile.getMaxThreshold()));

			profile.getRules().forEach(profileRule -> ruleEngine.registerRule(ruleFactory.getRule(profileRule.getRuleName()), profileRule.getSettings()));
		} else {
			LOGGER.info("No profile found; accountId = {}", accountId);

			ruleEngine = new DefaultRuleEngine(new DefaultDecisionHelper(0, 0));
		}

		return ruleEngine.process(context, stage);
	}

}

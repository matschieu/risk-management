package riskmanagement.scoring;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riskmanagement.common.RuleSettings;

/**
 *
 * @author Matschieu
 *
 */
public class DefaultRuleEngine implements RuleEngine {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRuleEngine.class);

	private final List<Action> actions = new ArrayList<>();

	private final List<RuleWrapper> rules = new ArrayList<>();

	private final DecisionHelper decisionHelper;

	/**
	 *
	 * @throws IllegalArgumentException
	 */
	public DefaultRuleEngine(DecisionHelper decisionHelper) throws IllegalArgumentException {
		if (decisionHelper == null) {
			throw new IllegalArgumentException("A decision helper must be provided");
		}
		this.decisionHelper = decisionHelper;
	}

	@Override
	public DecisionHelper getDecisionHelper() {
		return decisionHelper;
	}

	@Override
	public void registerAction(Action action) {
		this.actions.add(action);
	}

	@Override
	public void removeAction(Action step) {
		this.actions.remove(step);
	}

	@Override
	public List<Action> getActions() {
		return this.actions;
	}

	@Override
	public void registerRule(Rule rule, RuleSettings ruleSettings) {
		RuleWrapper ruleWrapper = new RuleWrapper();
		ruleWrapper.setRule(rule);
		ruleWrapper.setRuleSettings(ruleSettings);
		this.rules.add(ruleWrapper);
	}

	@Override
	public void removeRule(Rule rule) {
		this.rules.remove(rule);
	}

	@Override
	public List<Rule> getRules() {
		final List<Rule> singleRules = new ArrayList<>(rules.size());
		rules.forEach(ruleWrapper -> singleRules.add(ruleWrapper.getRule()));
		return singleRules;
	}

	@Override
	public <C> Result process(C context) {
		List<RuleResult> ruleResults = new ArrayList<>();

		LOGGER.info("Execute pre-actions");

		actions.forEach(action -> {
			LOGGER.info("Executing action {}", action.getName());
			action.executePreProcessing(this.getRules(), context);
		});

		LOGGER.info("Evaluate rules");

		for(RuleWrapper ruleWrapper : rules) {
			Rule rule = ruleWrapper.getRule();
			RuleSettings ruleSettings = ruleWrapper.getRuleSettings();

			LOGGER.info("Evaluating rule {}", rule.getName());

			RuleResult ruleResult;

			if (rule.isApplicable(context, ruleSettings)) {
				ruleResult = rule.evaluate(context, ruleSettings);
			} else {
				LOGGER.info("Not applicable");
				ruleResult = new RuleResult(rule.getName());
			}

			ruleResults.add(ruleResult);
		}

		double score = decisionHelper.calculateFinalScore(ruleResults);
		Color color = decisionHelper.calculateColor(score);

		Result result = new Result(score, color, ruleResults);

		LOGGER.info("Execute post-actions");

		actions.forEach(action -> {
			LOGGER.info("Executing action {}", action.getName());
			action.executePostProcessing(this.getRules(), context, result);
		});

		return result;
	}

}

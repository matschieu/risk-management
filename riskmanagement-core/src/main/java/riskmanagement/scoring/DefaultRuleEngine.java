package riskmanagement.scoring;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riskmanagement.common.RuleSettings;
import riskmanagement.common.Stage;

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
	public <C> Result process(C context, Stage stage) {
		List<RuleResult> ruleResults = new ArrayList<>();
		final Counter preProcessingActionCounter = new Counter();
		final Counter postProcessingActionCounter = new Counter();
		final Counter ruleCounter = new Counter();
		final Counter notApplicableRuleCounter = new Counter();

		LOGGER.info("Execute pre-actions");

		// Apply the pre-processing actions
		actions.stream()
			.filter(action -> action.isPreProcessingApplicable(context, stage))
			.forEach(action -> {
				LOGGER.info("Executing action {}", action.getName());
				action.executePreProcessing(this.getRules(), context, stage);
				preProcessingActionCounter.increment();
			});

		LOGGER.debug("Number of pre-processing action applied = {}", preProcessingActionCounter.getValue());
		LOGGER.info("Evaluate rules");

		// Apply the rules on the context to evaluate the risk
		for(RuleWrapper ruleWrapper : rules) {
			Rule rule = ruleWrapper.getRule();
			RuleSettings ruleSettings = ruleWrapper.getRuleSettings();

			LOGGER.info("Evaluating rule {}", rule.getName());

			RuleResult ruleResult;

			if (rule.isApplicable(context, stage, ruleSettings)) {
				ruleResult = rule.evaluate(context, stage, ruleSettings);
				ruleCounter.increment();
			} else {
				LOGGER.info("Rule {} is not applicable", rule.getName());
				ruleResult = new RuleResult(rule.getName());
				notApplicableRuleCounter.increment();
			}

			ruleResults.add(ruleResult);
		}

		LOGGER.debug("Number of rules applied = {} ; number of not applicable rules = {}", ruleCounter.getValue(), notApplicableRuleCounter.getValue());

		double score = decisionHelper.calculateFinalScore(ruleResults);
		Color color = decisionHelper.calculateColor(score);

		// Only count the number of actions applied in post-processing
		actions.stream()
			.filter(action -> action.isPostProcessingApplicable(context, stage))
			.forEach(a -> { postProcessingActionCounter.increment(); });

		// The final result to return
		Result result = new Result(score, color, ruleResults, preProcessingActionCounter.getValue(), postProcessingActionCounter.getValue());

		LOGGER.info("Execute post-actions");

		// Apply the post-processing actions
		actions.stream()
			.filter(action -> action.isPostProcessingApplicable(context, stage))
			.forEach(action -> {
				LOGGER.info("Executing action {}", action.getName());
				action.executePostProcessing(this.getRules(), context, stage, result);
			});

		LOGGER.debug("Number of post-processing action applied = {}", postProcessingActionCounter.getValue());

		return result;
	}

}

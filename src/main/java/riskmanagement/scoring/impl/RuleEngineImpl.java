package riskmanagement.scoring.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riskmanagement.scoring.api.Action;
import riskmanagement.scoring.api.Color;
import riskmanagement.scoring.api.DecisionHelper;
import riskmanagement.scoring.api.Result;
import riskmanagement.scoring.api.Rule;
import riskmanagement.scoring.api.RuleEngine;
import riskmanagement.scoring.api.RuleResult;

/**
 *
 * @author mathieu
 *
 */
public class RuleEngineImpl<C> implements RuleEngine<C> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RuleEngineImpl.class);

	private final List<Action<C>> actions = new ArrayList<>();
	private final List<Rule<C>> rules = new ArrayList<>();
	private final DecisionHelper decisionHelper;

	/**
	 *
	 * @throws IllegalArgumentException
	 */
	public RuleEngineImpl(DecisionHelper decisionHelper) throws IllegalArgumentException {
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
	public void registerAction(Action<C> action) {
		this.actions.add(action);
	}

	@Override
	public void removeAction(Action<C> step) {
		this.actions.remove(step);
	}

	@Override
	public List<Action<C>> getActions() {
		return this.actions;
	}

	@Override
	public void registerRule(Rule<C> rule) {
		this.rules.add(rule);
	}

	@Override
	public void removeRule(Rule<C> rule) {
		this.rules.remove(rule);
	}

	@Override
	public List<Rule<C>> getRules() {
		return this.rules;
	}

	@Override
	public Result process(C context) {
		List<RuleResult> ruleResults = new ArrayList<>();

		LOGGER.info("Execute pre-actions");

		actions.forEach(action -> {
			LOGGER.info("Executing action {}", action.getName());
			action.executePreProcessing(rules, context);
		});

		LOGGER.info("Evaluate rules");

		for(Rule<C> rule : rules) {
			LOGGER.info("Evaluating rule {}", rule.getName());

			RuleResult ruleResult;

			if (rule.isApplicable(context)) {
				ruleResult = rule.evaluate(context);
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
			action.executePostProcessing(rules, context, result);
		});

		return result;
	}

}

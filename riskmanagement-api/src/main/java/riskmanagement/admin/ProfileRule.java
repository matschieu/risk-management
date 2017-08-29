package riskmanagement.admin;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import riskmanagement.common.RuleSettings;

/**
 * @author Matschieu
 *
 */
public class ProfileRule {

	private String ruleName;

	private RuleSettings settings;

	/**
	 *
	 * @param ruleName
	 */
	private ProfileRule(String ruleName) {
		this(ruleName, new RuleSettings());
	}

	/**
	 *
	 * @param ruleName
	 * @param settings
	 */
	private ProfileRule(String ruleName, RuleSettings settings) {
		this.settings = settings;
	}

	/**
	 * @return String
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * @param ruleName
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * @return RuleSettings
	 */
	public RuleSettings getSettings() {
		return settings;
	}

	/**
	 * @param settings
	 */
	public void setSettings(RuleSettings settings) {
		this.settings = settings;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

}

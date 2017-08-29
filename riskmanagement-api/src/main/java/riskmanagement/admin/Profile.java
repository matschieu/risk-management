package riskmanagement.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import riskmanagement.common.Settings;
import riskmanagement.common.Stage;

/**
 * @author Matschieu
 *
 */
public class Profile {

	private Integer id;

	private String accountId;

	private String name;

	private double minThreshold;

	private double maxThreshold;

	private List<ProfileRule> rules = new ArrayList<>();

	private Settings settings = new Settings();

	private Stage stage;

	/**
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return String
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return double
	 */
	public double getMinThreshold() {
		return minThreshold;
	}

	/**
	 * @param minThreshold
	 */
	public void setMinThreshold(double minThreshold) {
		this.minThreshold = minThreshold;
	}

	/**
	 * @return double
	 */
	public double getMaxThreshold() {
		return maxThreshold;
	}

	/**
	 * @param maxThreshold
	 */
	public void setMaxThreshold(double maxThreshold) {
		this.maxThreshold = maxThreshold;
	}

	/**
	 * @return List<ProfileRule>
	 */
	public List<ProfileRule> getRules() {
		return rules;
	}

	/**
	 * @param rules
	 */
	public void setRules(List<ProfileRule> rules) {
		this.rules = rules;
	}

	/**
	 * @param settings
	 */
	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	/**
	 * @return Settings
	 */
	public Settings getSettings() {
		return settings;
	}

	/**
	 * @return Stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @param stage
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

}

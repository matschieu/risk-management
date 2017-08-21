package riskmanagement.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import riskmanagement.common.Settings;

/**
 * @author mathieu
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

	/**
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id (Integer)
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
	 * @param accountId (String)
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
	 * @param name (String)
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
	 * @param minThreshold (double)
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
	 * @param maxThreshold (double)
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
	 * @param rules (List<ProfileRule>)
	 */
	public void setRules(List<ProfileRule> rules) {
		this.rules = rules;
	}

	/**
	 * @param settings (Settings)
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

}

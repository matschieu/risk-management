package riskmanagement.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Matschieu
 *
 */
public class Settings {

	private final Map<String, Object> values = new HashMap<String, Object>();

	/**
	 *
	 */
	public Settings() { }

	/**
	 *
	 * @param values
	 */
	public Settings(Map<String, Object> values) {
		this.values.putAll(values);
	}

	/**
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getSettings() {
		return values;
	}

	/**
	 *
	 * @param key
	 * @param value
	 */
	public void putValue(String key, String value) {
		values.put(key, value);
	}

	/**
	 *
	 * @param key
	 * @return Object
	 */
	public Object removeValue(String key) {
		return values.remove(key);
	}

	/**
	 *
	 * @param key
	 * @return Object
	 */
	public Object getValue(String key) {
		return values.getOrDefault(key, null);
	}

	/**
	 *
	 * @param key
	 * @return Object
	 */
	public Object containsValue(String key) {
		return values.getOrDefault(key, null);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

}

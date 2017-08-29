package riskmanagement.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * The settings represents a list a key/value couples representing the configuration of an entity.
 *
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
	 * @param values: the list of key/value couples
	 */
	public Settings(Map<String, Object> values) {
		this.values.putAll(values);
	}

	/**
	 * @return Map<String,Object>: the list of key/value couples
	 */
	public Map<String, Object> getSettings() {
		return values;
	}

	/**
	 *
	 * @param key: the key of the value to add (replace the existing)
	 * @param value: the value associated to the key
	 */
	public void putValue(String key, String value) {
		values.put(key, value);
	}

	/**
	 *
	 * @param key: the key of the value to remove
	 * @return Object: the removed value associated to the key (null if the key doesn't exist)
	 */
	public Object removeValue(String key) {
		return values.remove(key);
	}

	/**
	 *
	 * @param key: the key of the value to get
	 * @return Object: the value associated to the key (null if the key doesn't exist)
	 */
	public Object getValue(String key) {
		return values.getOrDefault(key, null);
	}

	/**
	 *
	 * @param key: the key to check
	 * @return boolean: true if the key exists
	 */
	public boolean contains(String key) {
		return values.containsKey(key);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

}

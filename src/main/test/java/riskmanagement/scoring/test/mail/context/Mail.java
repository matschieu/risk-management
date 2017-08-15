package riskmanagement.scoring.test.mail.context;

/**
 *
 * @author mathieu
 *
 */
public class Mail {

	private String header;

	private String recipient;

	private String from;

	private String object;

	private String body;

	/**
	 *
	 * @return String
	 */
	public String getHeader() {
		return header;
	}

	/**
	 *
	 * @param header
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 *
	 * @return String
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 *
	 * @param recipient
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/**
	 *
	 * @return String
	 */
	public String getFrom() {
		return from;
	}

	/**
	 *
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 *
	 * @return String
	 */
	public String getObject() {
		return object;
	}

	/**
	 *
	 * @param object
	 */
	public void setObject(String object) {
		this.object = object;
	}

	/**
	 *
	 * @return String
	 */
	public String getBody() {
		return body;
	}

	/**
	 *
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}

}

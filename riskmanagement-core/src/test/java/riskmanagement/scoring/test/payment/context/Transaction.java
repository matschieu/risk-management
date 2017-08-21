package riskmanagement.scoring.test.payment.context;


/**
 *
 * @author Matschieu
 *
 */
public class Transaction {

	private Card card;

	/**
	 *
	 */
	public Transaction() {
		this(null);
	}

	/**
	 *
	 * @param card
	 */
	public Transaction(Card card) {
		this.card = card;
	}

	/**
	 *
	 * @return Card
	 */
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

}

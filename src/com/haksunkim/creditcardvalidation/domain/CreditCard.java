package com.haksunkim.creditcardvalidation.domain;

public class CreditCard {
	private String cardType;
	private String cardNumber;
	private boolean isValid;
	
	public CreditCard(String cardNumber) {
		setCardNumber(cardNumber);
	}
	
	// prevent credit card object created without card number
	private CreditCard() {}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		// when setting card number, remove space from user input
		this.cardNumber = cardNumber.replaceAll(" ", "");
	}
	
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(cardType);
		sb.append(": ");
		sb.append(cardNumber);
		if (isValid) sb.append(" (valid)");
		else sb.append(" (invalid)");
		
		return sb.toString();
	}
}

package com.haksunkim.creditcardvalidation;

import java.util.HashMap;

import com.haksunkim.creditcardvalidation.domain.CreditCard;

public class CreditCardValidator {
	private static final HashMap typeMap = new HashMap();
	
	
	/**
	 * Returns CreditCard with validation result
	 * @param	cardNumber	credit card number
	 * @return	CreditCard object 	 
	 */
	public static CreditCard validate(String cardNumber) {
		CreditCard creditCard = new CreditCard(cardNumber);
		
		// check card type using starting digits and length, and update Credit Card
		creditCard = validatePattern(creditCard);
		
		// check Luhn algorithm, and update Credit Card
		if (creditCard.isValid()) creditCard.setValid(validateByLuhnAlgorithm(creditCard.getCardNumber()));
		
		return creditCard;
	}
	
	/**
	 * Validate card number with Luhn algorithm
	 * @param	creditCard
	 * @return	CreditCard
	 */
	private static boolean validateByLuhnAlgorithm(String cardNumber) {
		int numberLength = cardNumber.length();
		int[] cardDigits = new int[numberLength];
		String numberReversed = new StringBuilder(cardNumber).reverse().toString();
		
		// store card digits as short in reverse order
		for (int ii = 0; ii < numberLength; ii ++) {
			cardDigits[ii] = Short.valueOf(numberReversed.substring(ii,ii+1));
		}
		
		// doubles every 2nd digits
		for (int jj = 1; jj < numberLength; jj = jj + 2) {
			cardDigits[jj] = cardDigits[jj] * 2;
		}
		
		// sum digits (digit greater than 9, need to split and sum the independetly) 
		int sumOfDigits = 0;
		for (int kk = 0; kk < numberLength; kk++) {
			if (cardDigits[kk] > 9) {
				sumOfDigits += (1 + cardDigits[kk] - 10);
			} else {
				sumOfDigits += cardDigits[kk];
			}
		}
		
		// if total is a multiple of 10, number is valid
		if (sumOfDigits%10 == 0) return true;
		
		return false;
	}
	
	/**
	 * Validate CreditCard with pattern and length, and return updated CreditCard
	 * @param	creditCard	CreditCard object
	 * @return	creditCard	updated CreditCard object
	 */
	private static CreditCard validatePattern(CreditCard creditCard) {
		String cardNumber = creditCard.getCardNumber();
		
		try {
			// get first 4 characters, and convert to short
			short startDigits = Short.valueOf(cardNumber.substring(0,4));
			if ((startDigits >= 3400 && startDigits <3500)
			 || (startDigits >= 3700 && startDigits < 3800)) creditCard.setCardType("AMEX");
			else if (startDigits == 6011) creditCard.setCardType("Discover");
			else if (startDigits >= 5100 && startDigits < 5600) creditCard.setCardType("MasterCard");
			else if (startDigits >= 4000 && startDigits < 5000) creditCard.setCardType("VISA");
			else creditCard.setCardType("Unknown");
			
			creditCard.setValid(false);
			
			// check length based on card type
			String cardType = creditCard.getCardType();
			if (cardType.equals("AMEX") && cardNumber.length() == 15) creditCard.setValid(true);
			else if (cardType.equals("Discover") && cardNumber.length() == 16) creditCard.setValid(true);
			else if (cardType.equals("MasterCard") && cardNumber.length() == 16) creditCard.setValid(true);
			else if (cardType.equals("VISA")
	              && (cardNumber.length() == 13 || cardNumber.length() == 16)) creditCard.setValid(true);
			else if (cardType.equals("Unknown")) creditCard.setValid(true); // set it as valid when type is unknown
		} catch(NumberFormatException nfe) {
			// this exception will be thrown when card number cannot be converted to number
			creditCard.setCardType("Invalid");
			creditCard.setValid(false);
		}
		
		return creditCard;
	}
}

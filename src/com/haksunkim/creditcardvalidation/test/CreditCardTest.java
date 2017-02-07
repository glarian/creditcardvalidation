package com.haksunkim.creditcardvalidation.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.haksunkim.creditcardvalidation.domain.CreditCard;

public class CreditCardTest {

	@Test
	public void testCardSetupWithSpaces() {
		CreditCard creditCard = new CreditCard("4111 1111 1111 1111");
		creditCard.setCardType("VISA");
		creditCard.setValid(true);
		
		assertEquals("VISA: 4111111111111111 (valid)", creditCard.toString());
	}

}

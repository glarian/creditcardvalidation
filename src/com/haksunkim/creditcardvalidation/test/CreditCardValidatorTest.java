package com.haksunkim.creditcardvalidation.test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import com.haksunkim.creditcardvalidation.CreditCardValidator;
import com.haksunkim.creditcardvalidation.domain.CreditCard;


public class CreditCardValidatorTest {

	@Test
	public void testLuhnAlgorithm() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		CreditCard creditCard = new CreditCard("4408 0412 3456 7893");
		Method method = CreditCardValidator.class.getDeclaredMethod("validateByLuhnAlgorithm", String.class);
		method.setAccessible(true);
		boolean isValid = (boolean) method.invoke(boolean.class,creditCard.getCardNumber());
		assertEquals(true,isValid);
	}
	
	@Test
	public void testCreditCard1() {
		CreditCard creditCard = CreditCardValidator.validate("4111111111111111");
		assertEquals("VISA: 4111111111111111 (valid)",creditCard.toString());
	}
	
	@Test
	public void testCreditCard2() {
		CreditCard creditCard = CreditCardValidator.validate("4111111111111");
		assertEquals("VISA: 4111111111111 (invalid)",creditCard.toString());
	}
	
	@Test
	public void testCreditCard3() {
		CreditCard creditCard = CreditCardValidator.validate("4012888888881881");
		assertEquals("VISA: 4012888888881881 (valid)",creditCard.toString());
	}
	
	@Test
	public void testCreditCard4() {
		CreditCard creditCard = CreditCardValidator.validate("378282246310005");
		assertEquals("AMEX: 378282246310005 (valid)",creditCard.toString());
	}
	
	@Test
	public void testCreditCard5() {
		CreditCard creditCard = CreditCardValidator.validate("6011111111111117");
		assertEquals("Discover: 6011111111111117 (valid)",creditCard.toString());
	}
	
	@Test
	public void testCreditCard6() {
		CreditCard creditCard = CreditCardValidator.validate("5105105105105100");
		assertEquals("MasterCard: 5105105105105100 (valid)",creditCard.toString());
	}
	
	@Test
	public void testCreditCard7() {
		CreditCard creditCard = CreditCardValidator.validate("5105 1051 0510 5106");
		assertEquals("MasterCard: 5105105105105106 (invalid)",creditCard.toString());
	}
	
	@Test
	public void testCreditCard8() {
		CreditCard creditCard = CreditCardValidator.validate("9111111111111111");
		assertEquals("Unknown: 9111111111111111 (invalid)",creditCard.toString());
	}
	
	@Test
	public void testInvalidNumber() {
		CreditCard creditCard = CreditCardValidator.validate("A123456789012");
		assertEquals("Invalid: A123456789012 (invalid)",creditCard.toString());
	}
}

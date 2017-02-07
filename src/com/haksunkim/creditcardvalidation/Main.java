package com.haksunkim.creditcardvalidation;

import java.util.Scanner;

import com.haksunkim.creditcardvalidation.domain.CreditCard;

public class Main {

	public static void main(String[] args) {
		// read user input
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter credir card number: ");
		String cardNumber = scanner.nextLine();
		
		CreditCard creditCard = CreditCardValidator.validate(cardNumber);
		System.out.println(creditCard.toString());
	}
}
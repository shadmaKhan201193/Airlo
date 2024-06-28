package com.itl.controller;

import org.springframework.stereotype.Service;

@Service
public class ICICIBankClass implements BankInterface {

	@Override
	public void test() {
		System.out.println("inside ICICIBankClass test method");

		
	}

}

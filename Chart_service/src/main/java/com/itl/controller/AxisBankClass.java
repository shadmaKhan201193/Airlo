package com.itl.controller;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AxisBankClass implements BankInterface {

	@Override
	public void test() {
		System.out.println("inside AxisBankClass test method");

	}

}

package com.itl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
	@Autowired 
	@Qualifier("ICICIBankClass")
		private  BankInterface bankInterface;

			@RequestMapping(value="/Test",method=RequestMethod.GET)
			public String test()
			{
				bankInterface.test();
				return "OK";
			}
}

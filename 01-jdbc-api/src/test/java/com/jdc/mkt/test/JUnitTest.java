package com.jdc.mkt.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitTest {

	@BeforeAll
	static void beforeall() {
		System.out.println("Before All Method");
	}
	@Test
	void testTwo() {
		System.out.println("Test Two Method");
	}

	@AfterAll
	static void afterall() {
		System.out.println("After All Method");
	}

	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each Method");
	}

	@AfterEach
	void afterEach() {
		System.out.println("After Each Method");
	}

	@Test
	void testOne() {
		System.out.println("Test One Method");
	}

	
}

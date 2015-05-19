package com.fudy;

import org.junit.Test;

public class CalculatorTest {
	@Test
	public void testAdd() {
		Calculator cal = new Calculator();
		cal.add(3, 4);
	}
}

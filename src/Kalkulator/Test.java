package Kalkulator;

import org.junit.Assert;
import Kalkulator.Calculator;

class Test {

	@org.junit.jupiter.api.Test
	void testInfixToOnp() {
		Assert.assertEquals(" 1 1 + 2 - ", Calculator.infixToOnp("1+1-2"));
		Assert.assertEquals(" 1 1 2 * + ", Calculator.infixToOnp("1+1*2"));
		Assert.assertEquals(" 3 2 1 + * ", Calculator.infixToOnp("3*(2+1)"));
		Assert.assertEquals(" 3 2 1 - 2 * * ", Calculator.infixToOnp("3*((2-1)*2)"));
		Assert.assertEquals(" 50 5 2 3 + / - ", Calculator.infixToOnp("50-5/(2+3)"));
		Assert.assertEquals(" 4 -2 + ", Calculator.infixToOnp("4 + -2"));
		Assert.assertEquals(" 0.5 1.5 3 + * ", Calculator.infixToOnp("0.5*(1.5+3)"));
	}
	
	@org.junit.jupiter.api.Test
	void testCalc() {
		Assert.assertEquals(3, (int)Calculator.calc("(3-2)*3"));
		Assert.assertEquals(-3, (int)Calculator.calc("3-2*3"));
		Assert.assertEquals(6, (int)Calculator.calc("3+3"));
		Assert.assertEquals(9, (int)Calculator.calc("3*(1-0)*3"));
		Assert.assertEquals(2, (int)Calculator.calc("(3.5-2,5)*2.0"));
	}

}

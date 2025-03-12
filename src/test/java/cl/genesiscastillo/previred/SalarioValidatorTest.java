package cl.genesiscastillo.previred;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cl.genesiscastillo.previred.validations.SalarioValidator;

public class SalarioValidatorTest {
	
	@Test
	public void test2() {
		SalarioValidator salarioValidator = new SalarioValidator();
		Assertions.assertFalse(salarioValidator.isValid(null, null));
		
		Assertions.assertFalse(salarioValidator.isValid(100L, null));
		
		Assertions.assertTrue(salarioValidator.isValid(4000001L, null));
	}
	
	@Test
	public void test1() {
		SalarioValidator salarioValidator = new SalarioValidator();
		salarioValidator.initialize(null);
	}

}

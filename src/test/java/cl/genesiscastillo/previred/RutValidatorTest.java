package cl.genesiscastillo.previred;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cl.genesiscastillo.previred.validations.RutValidator;

public class RutValidatorTest {
	
	@Test
	public void test1() {
		RutValidator rutValidator = new RutValidator();
		rutValidator.initialize(null);
	}
	
	@Test
	public void test2() {
		RutValidator rutValidator = new RutValidator();
		Assertions.assertTrue(rutValidator.isValid("1-9", null));
	}
	
	@Test
	public void test3() {
		RutValidator rutValidator = new RutValidator();
		Assertions.assertFalse(rutValidator.isValid("1", null));
	}
	
	@Test
	public void test4() {
		RutValidator rutValidator = new RutValidator();
		Assertions.assertFalse(rutValidator.isValid( null, null));
	}
	
	@Test
	public void test5() {
		RutValidator rutValidator = new RutValidator();
		Assertions.assertFalse(rutValidator.isValid( "", null));
	}

	@Test
	public void test6() {
		RutValidator rutValidator = new RutValidator();
		Assertions.assertFalse(rutValidator.isValid( "1-2", null));
	}

	

}

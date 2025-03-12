package cl.genesiscastillo.previred.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SalarioValidator implements ConstraintValidator<ValidaSalario, Long> {
	
    @Override
    public void initialize(ValidaSalario constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long salario, ConstraintValidatorContext context) {
        if (salario == null || (salario < 400000)) {
            return false;
        }
        return true;
    }

}

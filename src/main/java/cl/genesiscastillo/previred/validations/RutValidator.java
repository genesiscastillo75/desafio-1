package cl.genesiscastillo.previred.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RutValidator implements ConstraintValidator<ValidRut, String> {
	
    @Override
    public void initialize(ValidRut constraintAnnotation) {
    }

    @Override
    public boolean isValid(String rut, ConstraintValidatorContext context) {
        if (rut == null || rut.isEmpty()) {
            return false;
        }
        return esRutValido(rut);
    }

    private boolean esRutValido(String rut) {
        try {
        	if(!rut.contains("-")) {
        		return false;
        	}
            rut = rut.toUpperCase();
            rut = rut.replace(".", "").replace("-", "");
            if(rut.isEmpty()) {
            	return false;
            }
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                return true;
            }

        } catch (NumberFormatException e) {
        	
        }
        return false;
    }
}

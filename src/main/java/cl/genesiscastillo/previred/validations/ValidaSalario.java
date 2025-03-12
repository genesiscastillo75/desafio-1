package cl.genesiscastillo.previred.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SalarioValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidaSalario {
    String message() default "No permitir salarios base menores a $400,000";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package br.jus.cnj.inova.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = NumeroProcessoValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroProcessoConstraint {
    String message() default "NPU inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

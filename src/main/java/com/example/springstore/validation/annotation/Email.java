package com.example.springstore.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface Email {
    String message() default "{email.notValid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Validates that a given street is valid. The validation uses the regular expression:
 * <b>^$|^([0-9]+ )?[A-Z ]+[A-Z ,\.]+$</b>.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Documented
@Size(min = 5, max = 255)
@Pattern(regexp = "^$|^([0-9]+ )?[A-Z ]+[A-Z ,\\.]+$")
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface Street {

    String message() default "{com.udm.health.persistence.validation.Street.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

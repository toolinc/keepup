// Copyright 2014 University of Detroit Mercy.

package com.udm.health.validation;

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

/**
 * Validates that a city name is upper case and its length should be more that five characters but
 * less than fifty. The validation uses the regular expression: <b>^$|^[A-Z ]{5,50}$</b>.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Documented
@Pattern(regexp = "^$|^[A-Z ]{5,50}$")
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface City {

    String message() default "{com.udm.health.persistence.validation.City.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

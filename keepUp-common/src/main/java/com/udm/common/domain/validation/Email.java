// Copyright 2014 University of Detroit Mercy.

package com.udm.common.domain.validation;

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
 * Validates that a given email address is valid. The validation uses the regular expression:
 * <b>\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*</b>.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Documented
@Pattern(regexp = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface Email {

    String message() default "{com.udm.common.domain.validation.Email.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
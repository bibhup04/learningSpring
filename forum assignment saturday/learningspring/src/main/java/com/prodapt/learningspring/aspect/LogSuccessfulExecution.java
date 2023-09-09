package com.prodapt.learningspring.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogSuccessfulExecution {
    String value() default ""; // Your original value attribute for the custom message
    String userId() default ""; // Attribute for userId
    String postId() default ""; // Attribute for postId
    String message() default ""; // Attribute for message
}

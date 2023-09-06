package com.prodapt.learningspring.controller.exception;

public class SocialMediaBusinessException extends RuntimeException{
	public SocialMediaBusinessException(String message) {
        super(message);
    }
}
package com.prodapt.learningspring.business;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prodapt.learningspring.business.LoggedInUser;
import com.prodapt.learningspring.business.NeedsAuth;

@Aspect
@Component
public class AuthAspect {

    @Autowired
    private LoggedInUser loggedInUser;

    @Around("@annotation(needsAuth)")
    public Object demandLogin(ProceedingJoinPoint pjp, NeedsAuth needsAuth) throws Throwable {
        if (loggedInUser.getLoggedInUser() != null) {
            return pjp.proceed();
        }
        return String.format("redirect:%s", needsAuth.loginPage());
    }
    
}

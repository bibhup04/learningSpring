package com.prodapt.learningspring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prodapt.learningspring.controller.binding.NotificationData;
import com.prodapt.learningspring.service.NotificationService;

@Aspect
@Component
public class LogSuccessfulExecutionAspect {

    @Autowired
    private NotificationService notificationService;

    @AfterReturning(value = "@annotation(com.prodapt.learningspring.aspect.LogSuccessfulExecution)", returning = "result")
    public void logSuccessfulExecution(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        //String message = logSuccessfulExecution.message();
        System.out.println("Method '" + methodName + "' was successfully executed." );
    }


    @AfterReturning(pointcut = "@annotation(logSuccessfulExecution)", returning = "returnValue")
    public void logSuccessfulExecution(JoinPoint joinPoint, LogSuccessfulExecution logSuccessfulExecution, Object returnValue) {
        if (returnValue instanceof NotificationData) {
            NotificationData notificationData = (NotificationData) returnValue;
            notificationService.createNotification(
                notificationData.getUser(),
                notificationData.getPost(),
                notificationData.getEventType(),
                notificationData.getMessage()
            );
        }
        System.out.println("notification added");
    }



    //  @Before("@annotation(logSuccessfulExecution)")
    // public void logBefore(JoinPoint joinPoint, LogSuccessfulExecution logSuccessfulExecution) {
    //     String userId = logSuccessfulExecution.userId();
    //     String postId = logSuccessfulExecution.postId();
    //     String message = logSuccessfulExecution.message();

    //     // Log the captured attributes
    //     System.out.println("User ID: " + userId);
    //     System.out.println("Post ID: " + postId);
    //     System.out.println("Message: " + message);

    //     You can also access other method parameters if needed
    //     Object[] args = joinPoint.getArgs();
    //     for (Object arg : args) {
    //         // Process args as needed
    //     }
    // }
}

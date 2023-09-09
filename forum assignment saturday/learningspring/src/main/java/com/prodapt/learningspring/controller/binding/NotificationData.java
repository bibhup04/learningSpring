package com.prodapt.learningspring.controller.binding;

import com.prodapt.learningspring.entity.Post;
import com.prodapt.learningspring.entity.User;
import lombok.Data;

@Data
public class NotificationData {
    private User user;
    private Post post;
    private String eventType;
    private String message;
    
}

package com.prodapt.learningspring.model;

import lombok.Data;

@Data
public class CommentDTO {
    private String commentText;
    private int postId;
    
}

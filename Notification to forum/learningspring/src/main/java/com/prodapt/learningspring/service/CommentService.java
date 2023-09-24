package com.prodapt.learningspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodapt.learningspring.entity.Comment;
import com.prodapt.learningspring.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findByPostId(int postId) {
        return commentRepository.findByPostId(postId);
    }

    // Add other comment-related methods (e.g., saveComment, updateComment, deleteComment) here
}

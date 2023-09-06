package com.media.socialmedia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.media.socialmedia.entity.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer>{

	List<Comment> findAll();
}

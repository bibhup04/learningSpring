package com.prodapt.learningspring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.prodapt.learningspring.entity.Post;

public interface PostRepository extends CrudRepository<Post, Integer>{

    Optional<Post> findById(int authorId);
}

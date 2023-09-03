package com.media.socialmedia.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.media.socialmedia.entity.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer>{
	
	List<Post> findAll();

//	@Query("SELECT p.postText, p.postId, u.userName FROM Post p JOIN p.user u")
//    List<Object[]> findPostTextAndAuthor();
    
//	@Query("SELECT p.postText, p.postId, p.user.userName FROM Post p")
//	List<Object[]> findPostTextAndAuthor();
}

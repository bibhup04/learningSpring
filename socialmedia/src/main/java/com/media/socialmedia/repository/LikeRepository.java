package com.media.socialmedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.media.socialmedia.entity.LikeRecord;

@Repository
public interface LikeRepository extends CrudRepository<LikeRecord, Integer>{
	
	
//	 @Query("SELECT l FROM Like l WHERE l.likeIdComposite.post.post_id = :post_id")
//	    List<Like> findAllLikesByPostId(@Param("post_id") Integer post_id);
	
//	@Query(value = "select count(*) from like l where l.post_id = ?1")
//	int countByPostId(Integer postId);
	
	@Query(value = "select count(*) from like_record where post_id = ?1", nativeQuery=true)
	  Integer countByPostId(Integer postId);

}

package com.media.socialmedia.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.media.socialmedia.entity.LikeRecord;

public interface LikeCountRepository extends Repository<LikeRecord, Integer>{

//	@Query(value = "select count(*) from like l where l.likeId.post.post_id = ?1")
//	int countByPostId(Integer postId);
	
//	@Query("SELECT COUNT(l) FROM Like l WHERE l.likeIdComposite.post.post_Id = :post_Id")
//    int countLikesByPostId(@Param("post_Id") int postId);
}

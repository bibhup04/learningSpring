package com.media.socialmedia.entity;

import jakarta.persistence.Column; 
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class LikeRecord {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private int likesId;
	
	
    @EmbeddedId
    private LikeId likeIdComposite;


    public int getLikesId() {
        return likesId;
    }

    public void setLikesId(int likesId) {
        this.likesId = likesId;
    }

    public LikeId getLikeIdComposite() {
        return likeIdComposite;
    }

    public void setLikeIdComposite(LikeId likeIdComposite) {
        this.likeIdComposite = likeIdComposite;
    }
}


package com.project.entites;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="p_like")
@Data
public class Like {

    @Id
    Long id;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="post_id",nullable = false,insertable = false,updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Post post;
    @ManyToOne(optional=false)
    @JoinColumn(name="user_id",nullable = false,insertable = false,updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    User user;
}

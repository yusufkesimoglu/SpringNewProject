package com.project.entites;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


@Entity
@Table(name="user")
@Data
public class User {
    @Column(nullable=false)
    @Id
    Long id;

    String userName;

    String password;

}
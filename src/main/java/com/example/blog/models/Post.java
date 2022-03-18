package com.example.blog.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Post { // model
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, place, description;
    private int views;

    public Post() {}

    public Post(String title, String place, String description) {
        this.title = title;
        this.place = place;
        this.description = description;
    }
}

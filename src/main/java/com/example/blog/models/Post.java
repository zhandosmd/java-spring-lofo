package com.example.blog.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Post { // model
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, place, description;
    private String type;
    private int views;
    @Column(nullable = true, length = 64)
    private String photos;

    public Post() {}
    public Post(String title, String place, String description, String type) {
        this.title = title;
        this.place = place;
        this.description = description;
        this.type = type;
    }

    public Post(String title, String place, String description) {
        this.title = title;
        this.place = place;
        this.description = description;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;
        return "/user-photos/" + id + "/" + photos;
    }
}

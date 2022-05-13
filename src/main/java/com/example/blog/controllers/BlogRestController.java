package com.example.blog.controllers;

import com.example.blog.configurations.FileUploadUtil;
import com.example.blog.models.Post;
import com.example.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class BlogRestController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/api/blog")
    public Iterable<Post> blogMain(){
        Iterable<Post> posts = postRepository.findAll();
        return posts;
    }

    @PostMapping("/api/blog/add")
    public String savePost(
        @RequestParam String title,
        @RequestParam(required = false) String place,
        @RequestParam(required = false) String description,
        @RequestParam(required = false) String type,
        @RequestParam(required = false) MultipartFile image
    ) throws IOException {
        Post post = new Post(title, place, description, type);

        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        post.setPhotos(fileName);

        Post savedPost = postRepository.save(post);
        String uploadDir = "user-photos/" + savedPost.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, image);
        return "successfully added";
    }
    
    @GetMapping("/api/blog/{id}")
    public Post blogByID(@PathVariable(value = "id") long id){
        if(!postRepository.existsById(id)){
            return null;
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        return res.get(0);
    }

    @PutMapping("/api/blog/{id}/edit")
    public String blogPostUpdate(
            @PathVariable(value = "id") long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String place,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String type
    ) {
        Post post = postRepository.findById(id).orElseThrow();
        if(!title.isEmpty()) post.setTitle(title);
        if(!place.isEmpty()) post.setPlace(place);
        if(!description.isEmpty()) post.setDescription(description);
        if(!type.isEmpty()) post.setType(type);

        postRepository.save(post);

        return "successfully edited";
    }

    @DeleteMapping("/api/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "successfully deleted post number " + id;
    }
}


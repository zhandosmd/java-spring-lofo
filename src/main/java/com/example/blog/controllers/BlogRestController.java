package com.example.blog.controllers;

import com.example.blog.models.Post;
import com.example.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String blogPostAdd(
            @RequestParam("title") String title,
            @RequestParam("place") String place,
            @RequestParam("description") String description) {
        Post post = new Post(title, place, description);
        postRepository.save(post);

        return "succesfully added";
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

    @GetMapping("/api/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PutMapping("/api/blog/{id}/edit")
    public String blogPostUpdate(
            @PathVariable(value = "id") long id,
            @RequestParam String title,
            @RequestParam String place,
            @RequestParam String description)
    {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setPlace(place);
        post.setDescription(description);
        postRepository.save(post);

        return "successully edited";
    }

    @DeleteMapping("/api/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "successully deleted post number " + id;
    }
}

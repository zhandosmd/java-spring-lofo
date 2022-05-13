package com.example.blog.controllers;

import com.example.blog.configurations.LocalData;
import com.example.blog.models.Post;
import com.example.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model){
        return "main2";
    }

//    @GetMapping("/")
//    public String home(Model model, @RequestParam(required = false, defaultValue = "All", value="type") String type){
//        Iterable<Post> posts = postRepository.findAll();
//        model.addAttribute("posts", posts);
//        if(!type.equals("All")) {
//            List<Post> foundPosts = new ArrayList<Post>();
//            for(Post post: posts){
//                if(post.getType().equals(type)){
//                    foundPosts.add(post);
//                }
//            }
//            Iterable<Post> iterable = foundPosts;
//            model.addAttribute("posts", iterable);
//        }
//        List<String> types = new LocalData().types;
//        model.addAttribute("types", types);
//        model.addAttribute("selectedType", type);
//        return "allnews2";
//    }

    @GetMapping("/opinions")
    public String opinions(Model model) {
        return "opinions2";
    }

    @GetMapping("/analytics")
    public String analytics(Model model) {
        return "analytics2";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Страница про нас");
        return "about";
    }
}

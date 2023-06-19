package com.database.reddit.controller;

import com.database.reddit.Dto.PostDto;
import com.database.reddit.entity.Post;
import com.database.reddit.service.PostsService;
import com.database.reddit.service.interfaces.FileService;
import jakarta.transaction.NotSupportedException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class PostsController {
    private final FileService fileService;
    private final PostsService postsService;
    @GetMapping("/")
    public String viewCommunity(){
        return "Community-Settings";
    }

    @GetMapping("/post/{postId}")
    public String viewPost(Model model, @PathVariable Long postId){
        Post post = postsService.viewPost(postId);
        model.addAttribute("post",post);
        return "viewpost";
    }

    @GetMapping("/submit")
    public String createPost(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "new-post";
    }

    @PostMapping("/submit")
    public String createPost(
            @RequestPart(name = "file") MultipartFile[] file,
            @ModelAttribute PostDto postDto
    ) throws IOException, NotSupportedException {
        Post post = postsService.savePost(postDto, file);
        return "success";
    }

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        System.out.println("image controller");
        Resource file = fileService.load(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}

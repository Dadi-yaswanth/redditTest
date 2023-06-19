package com.database.reddit.service;

import com.database.reddit.Dto.PostDto;
import com.database.reddit.entity.Media;
import com.database.reddit.entity.Post;
import com.database.reddit.entity.User;
import com.database.reddit.repository.PostRepository;
import com.database.reddit.repository.UserRepository;
import jakarta.transaction.NotSupportedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostRepository postRepository;
    private final FileServiceImpl fileService;
    private final UserRepository userRepository;

    public Post viewPost(Long postId) {
        Post post = postRepository.findById(postId).get();
        return post;
    }

    public Post savePost(PostDto postDto, MultipartFile[] file) throws IOException, NotSupportedException {
        Post post = new Post();
        User user = userRepository.findById(1L).orElseThrow();

        post.setUser(user);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setPublished(true);

        List<Media> mediaList = new ArrayList<>();
        if (file != null) {
            post.setMedia(true);
            for (MultipartFile multipartFile : file) {
                mediaList.add(fileService.uploadImage(multipartFile));
            }
        }

        post.setMediaList(mediaList);
        return postRepository.save(post);
    }
}

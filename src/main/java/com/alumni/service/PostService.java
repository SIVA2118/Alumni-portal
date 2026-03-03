package com.alumni.service;

import com.alumni.model.Comment;
import com.alumni.model.Post;
import com.alumni.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        post.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByTimestampDesc();
    }

    public void deletePost(String postId) {
        postRepository.deleteById(postId);
    }

    public Post updatePost(String postId, Post updatedPost) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        existingPost.setContent(updatedPost.getContent());
        if (updatedPost.getImageBase64() != null) {
            existingPost.setImageBase64(updatedPost.getImageBase64());
        }
        return postRepository.save(existingPost);
    }

    public Post toggleLike(String postId, String userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        
        if (post.getLikedBy().contains(userId)) {
            post.getLikedBy().remove(userId);
        } else {
            post.getLikedBy().add(userId);
        }
        return postRepository.save(post);
    }

    public Post addComment(String postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        
        comment.setId(java.util.UUID.randomUUID().toString());
        comment.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        post.getComments().add(comment);
        return postRepository.save(post);
    }

    public Post incrementShare(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        
        post.setShareCount(post.getShareCount() + 1);
        return postRepository.save(post);
    }
}

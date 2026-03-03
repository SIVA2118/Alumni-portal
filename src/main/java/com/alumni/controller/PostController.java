package com.alumni.controller;

import com.alumni.model.Comment;
import com.alumni.model.Post;
import com.alumni.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable String id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok("Post deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable String id, @RequestBody Post post) {
        try {
            return ResponseEntity.ok(postService.updatePost(id, post));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Post> toggleLike(@PathVariable String id, @RequestParam String userId) {
        return ResponseEntity.ok(postService.toggleLike(id, userId));
    }

    @PostMapping("/{id}/share")
    public ResponseEntity<Post> incrementShare(@PathVariable String id) {
        return ResponseEntity.ok(postService.incrementShare(id));
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<Post> addComment(@PathVariable String id, @RequestBody Comment comment) {
        return ResponseEntity.ok(postService.addComment(id, comment));
    }
}

package com.alumni.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String authorId;
    private String authorName;
    private String authorRole; // e.g., "ALUMNI" or "ADMIN"
    private String content; // The text content of the post
    private String imageBase64; // The attached image converted to Base64
    private String timestamp;
    private Set<String> likedBy = new HashSet<>();
    private int shareCount = 0;
    private List<Comment> comments = new ArrayList<>();
}

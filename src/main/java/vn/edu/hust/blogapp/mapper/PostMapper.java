package vn.edu.hust.blogapp.mapper;

import vn.edu.hust.blogapp.dto.PostDto;
import vn.edu.hust.blogapp.entity.Post;

public class PostMapper {
    public static Post mapToPost(PostDto postDto){
        Post post = new Post(
                postDto.getId(),
                postDto.getTitle(),
                postDto.getDescription(),
                postDto.getContent()
        );
        return post;
    }

    public static PostDto mapToPostDto(Post post){
        PostDto postDto = new PostDto(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getContent()
        );
        return postDto;
    }
}

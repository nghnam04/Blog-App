package vn.edu.hust.blogapp.mapper;

import vn.edu.hust.blogapp.dto.PostDto;
import vn.edu.hust.blogapp.entity.Post;

public class PostMapper {
    public static Post mapToPost(PostDto postDto){
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
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

package vn.edu.hust.blogapp.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.hust.blogapp.dto.PostDto;
import vn.edu.hust.blogapp.entity.Post;
import vn.edu.hust.blogapp.exception.ResourceNotFoundException;
import vn.edu.hust.blogapp.mapper.PostMapper;
import vn.edu.hust.blogapp.repository.PostRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;

    public PostDto createPost(PostDto postDto){
        Post post = postRepository.save(PostMapper.mapToPost(postDto));
        return PostMapper.mapToPostDto(post);
    }

    public List<PostDto> getAllPosts(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return postRepository.findAll(pageable).getContent().stream()
                .map((post) -> PostMapper.mapToPostDto(post))
                .collect(Collectors.toList());
    }

    public PostDto getPostById(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return PostMapper.mapToPostDto(post);
    }

    public PostDto updatePost(Long id, PostDto postDto){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return PostMapper.mapToPostDto(updatedPost);
    }

    public void deletePost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }
}

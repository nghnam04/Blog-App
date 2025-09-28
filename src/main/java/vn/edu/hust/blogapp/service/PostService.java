package vn.edu.hust.blogapp.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.hust.blogapp.dto.PostDto;
import vn.edu.hust.blogapp.dto.PostResponse;
import vn.edu.hust.blogapp.entity.Post;
import vn.edu.hust.blogapp.exception.ResourceNotFoundException;
import vn.edu.hust.blogapp.mapper.PostMapper;
import vn.edu.hust.blogapp.repository.PostRepository;

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

    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir){

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<PostDto> content =  posts.getContent().stream()
                .map((post) -> PostMapper.mapToPostDto(post))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse(
                content,
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.isLast()
        );

        return postResponse;
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

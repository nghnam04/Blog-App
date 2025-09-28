package vn.edu.hust.blogapp.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.edu.hust.blogapp.dto.CommentDto;
import vn.edu.hust.blogapp.entity.Comment;
import vn.edu.hust.blogapp.entity.Post;
import vn.edu.hust.blogapp.exception.BlogAPIException;
import vn.edu.hust.blogapp.exception.ResourceNotFoundException;
import vn.edu.hust.blogapp.mapper.CommentMapper;
import vn.edu.hust.blogapp.repository.CommentRepository;
import vn.edu.hust.blogapp.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentDto createComment(Long postId, CommentDto commentDto){
        Comment comment = CommentMapper.mapToComment(commentDto);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return CommentMapper.mapToCommentDto(newComment);
    }

    public List<CommentDto> getAllCommentsByPostId(Long postId){
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(comment -> CommentMapper.mapToCommentDto(comment))
                .collect(Collectors.toList());
    }

    public CommentDto getCommentById(Long postId, Long commentId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return CommentMapper.mapToCommentDto(comment);
    }

    public CommentDto updateComment(Long postId, Long commentId,CommentDto commentDto){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return CommentMapper.mapToCommentDto(updatedComment);
    }

    public void deleteComment(Long postId, Long commentId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        commentRepository.delete(comment);
    }
}

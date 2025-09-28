package vn.edu.hust.blogapp.mapper;

import vn.edu.hust.blogapp.dto.CommentDto;
import vn.edu.hust.blogapp.entity.Comment;

public class CommentMapper {
    public static CommentDto mapToCommentDto(Comment comment){
        CommentDto commentDto = new CommentDto(
                comment.getId(),
                comment.getName(),
                comment.getEmail(),
                comment.getBody()
        );
        return commentDto;
    }

    public static Comment mapToComment(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}

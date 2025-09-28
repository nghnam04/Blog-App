package vn.edu.hust.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.blogapp.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}

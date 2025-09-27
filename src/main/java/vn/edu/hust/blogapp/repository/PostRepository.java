package vn.edu.hust.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.blogapp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}

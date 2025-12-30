package com.example.cmsadmin.repository;

import com.example.cmsadmin.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 게시글 Repository
 *
 * ✔ DB 접근 전담
 * ✔ SQL 몰라도 CRUD 가능
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    // findAll(), save(), deleteById() 자동 제공
}

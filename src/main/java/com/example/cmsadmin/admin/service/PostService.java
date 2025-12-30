package com.example.cmsadmin.admin.service;

import com.example.cmsadmin.domain.Post;
import com.example.cmsadmin.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// 게시글 비즈니스 로직 담당
// 규칙
// 정책
// 상태 변경
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    //게시글 등록
    public void create(String title, String content) {
        Post post = new Post (title, content);
        //PostRepository.save(post);
    }

    public List<Post> list() {
        return postRepository.findAll();
    }

    // 게시글 삭제
    public void delete(Long id){
        postRepository.deleteById(id);
    }
    // 게시글 노출 토글
    public void toggleVisible(Long id){
        Post post = postRepository.findById(id).orElseThrow();

        post.toggleVisible();
        postRepository.save(post);
    }


}

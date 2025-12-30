package com.example.cmsadmin.admin;

import com.example.cmsadmin.domain.Post;
import com.example.cmsadmin.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// 관리자 게시판 컨트롤러
// admin/posts 로 돌아오는 요청 처리
@Controller
@RequestMapping("/admin/posts")
public class AdminPostController {
    private final PostRepository postRepository;

    public AdminPostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    // 게시글 목록
    // GET / admin/posts
    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "admin/post/list";
    }
    //게시글 등록 화면
    @GetMapping("/new")
    public String form() {
        return "admin/post/form";
    }
    //게시글 등록 처리
    @PostMapping
    public String save(
            @RequestParam String title,
            @RequestParam String content
    ){
        // 게시글 생성
        Post post = new Post(title, content);
        // DB 저장
        postRepository.save(post);
        // 저장 후 목록으로 이동 (중복 저장 방지)
        return "redirect:/admin/posts";
    }
    //게시글 삭제
    //Post/admin/posts/{id}/delete
    @PostMapping("{id}/delete")
    @ResponseBody // 화면이 아니라 응답만 반환
    public void delete(@PathVariable Long id) {
        //디비에서 게시글 삭제 @PathVariable 에있는 id값만 가져옴 @ResponseBody html X JSON 응답만 O
        postRepository.deleteById(id);
    }
// 게시글 노출 토글 (Ajax)
// POST/admin/posts/{id}/toggle
    @PostMapping("/{id}/toggle")
    @ResponseBody
    public void toggle(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow();

        // 노출 상태 반전
        post.toggleVisible();
        //변경내용 저장
        postRepository.save(post);
    }

}

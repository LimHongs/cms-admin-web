package com.example.cmsadmin.admin.controller;

import com.example.cmsadmin.admin.service.PostService;
import com.example.cmsadmin.domain.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// 관리자 게시판 컨트롤러
// admin/posts 로 돌아오는 요청 처리
@Controller
@RequestMapping("/admin/posts")
public class AdminPostController {

    private final PostService postService;

    public AdminPostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 목록
    // GET / admin/posts
    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postService.list());
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
        postService.create(title, content);
        // 저장 후 목록으로 이동 (중복 저장 방지)
        return "redirect:/admin/posts";
    }
    //게시글 삭제
    //Post/admin/posts/{id}/delete
    @PostMapping("{id}/delete")
    @ResponseBody // 화면이 아니라 응답만 반환
    public void delete(@PathVariable Long id) {
        //디비에서 게시글 삭제 @PathVariable 에있는 id값만 가져옴 @ResponseBody html X JSON 응답만 O
        postService.delete(id);
    }
// 게시글 노출 토글 (Ajax)
// POST/admin/posts/{id}/toggle
    @PostMapping("/{id}/toggle")
    @ResponseBody
    public void toggle(@PathVariable Long id) {
        postService.toggleVisible(id);
    }

}

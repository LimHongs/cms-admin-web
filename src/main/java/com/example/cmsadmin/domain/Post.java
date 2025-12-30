package com.example.cmsadmin.domain;

import jakarta.persistence.*;

/**
 * 게시글(Entity)
 *
 * ✔ DB 테이블과 1:1 매핑
 * ✔ CMS에서 관리하는 '핵심 데이터'
 */
@Entity
public class Post {

    /**
     * PK (Primary Key)
     * 게시글을 구분하는 고유 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 게시글 제목
     */
    private String title;

    /**
     * 게시글 내용
     * 기본 varchar(255)보다 길게
     */
    @Column(length = 2000)
    private String content;

    /**
     * 노출 여부
     * 삭제 대신 숨김(아카이브 개념)
     */
    private boolean visible = true;

    /**
     * JPA 전용 기본 생성자
     * (직접 new로 쓰지 못하게 protected)
     */
    protected Post() {}

    /**
     * 게시글 생성자
     * 게시글 생성 시 필수 값만 받음
     */
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * 노출 상태 토글
     * setter 대신 의미 있는 행위 메서드
     */
    public void toggleVisible() {
        this.visible = !this.visible;
    }

    // ===== Getter =====

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public boolean isVisible() { return visible; }
}

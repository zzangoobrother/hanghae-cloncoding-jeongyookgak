package com.hanghae.hanghaecloncodingjeongyookgak.controller;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.CommentRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 상품 리뷰 조회
    @GetMapping("/api/detail/review")
    public Map<String, Object> getComments(@RequestParam Long productId) {
        return commentService.getComments(productId);
    }

    // 상품 리뷰 등록
    @PostMapping("/api/detail/review")
    public Map<String, Object> createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetails userDetails) {
        return commentService.createComment(commentRequestDto, userDetails);
    }

    // 상품 리뷰 삭제
    @DeleteMapping("/api/detail/review/{commentId}")
    public Map<String, Object> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }
}

package com.hanghae.hanghaecloncodingjeongyookgak.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private Long commentId;
    private Long productId;
    private String title;
    private String content;
}

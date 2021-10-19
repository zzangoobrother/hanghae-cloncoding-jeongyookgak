package com.hanghae.hanghaecloncodingjeongyookgak.service;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.CommentRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.ErrorCode;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.HanghaeClonException;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Comment;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.CommentRepository;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.ProductRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    public CommentService(ProductRepository productRepository, CommentRepository commentRepository) {
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
    }

    public Map<String, Object> getComments(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND)
        );
        List<Comment> comments = commentRepository.findAllByProductOrderByModifiedDateDesc(product);

        Map<String, Object> result = new HashMap<>();
        result.put("comments", comments);
        result.put("result", "success");

        return result;
    }

    public Map<String, Object> createComment(CommentRequestDto commentRequestDto, UserDetails userDetails) {
        Product product = productRepository.findById(commentRequestDto.getProductId()).orElseThrow(
                () -> new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND)
        );
        System.out.println(product);
        Comment comment = new Comment(commentRequestDto.getTitle(), commentRequestDto.getContent(), "닉네임", product);
        Comment saveComment = commentRepository.save(comment);

        List<Comment> comments = commentRepository.findAllByProductOrderByModifiedDateDesc(saveComment.getProduct());

        Map<String, Object> result = new HashMap<>();
        result.put("comments", comments);
        result.put("result", "success");

        return result;
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

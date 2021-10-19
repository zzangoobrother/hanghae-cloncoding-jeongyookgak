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

import java.util.List;

@Service
public class CommentService {

    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    public CommentService(ProductRepository productRepository, CommentRepository commentRepository) {
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND)
        );
        return commentRepository.findAllByProductOrderByModifiedDateDesc(product);
    }

    public List<Comment> createComment(CommentRequestDto commentRequestDto, UserDetails userDetails) {
        Product product = productRepository.findById(commentRequestDto.getProductId()).orElseThrow(
                () -> new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND)
        );
        System.out.println(product);
        Comment comment = new Comment(commentRequestDto.getTitle(), commentRequestDto.getContent(), "닉네임", product);
        Comment saveComment = commentRepository.save(comment);

        return commentRepository.findAllByProductOrderByModifiedDateDesc(saveComment.getProduct());
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

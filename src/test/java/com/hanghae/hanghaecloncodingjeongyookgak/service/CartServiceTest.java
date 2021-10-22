package com.hanghae.hanghaecloncodingjeongyookgak.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.hanghaecloncodingjeongyookgak.dto.CartRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Cart;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import com.hanghae.hanghaecloncodingjeongyookgak.model.ProductCategory;
import com.hanghae.hanghaecloncodingjeongyookgak.model.User;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.CartRepository;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.ProductRepository;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.UserRepository;
import com.hanghae.hanghaecloncodingjeongyookgak.security.UserDetailsImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    CartRepository cartRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("새로운 Cart 객체 추가")
    void addCart(){

        // given
        Long productId = 100L;
        Long count = 10L;

        String nickname = "nickname" ;
        String email = "abcde@naver.com";
        String password = "abc123!@#";


        Product product = new Product(ProductCategory.PORK,"title",10L,"image","imagedetail");
        User user = new User(email,password,nickname);
        user.setId(10L);
        Cart cart = new Cart(count,product,user);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        CartRequestDto cartRequestDto = new CartRequestDto(
                productId,
                count
        );

        CartService cartService = new CartService(cartRepository, productRepository, userRepository);

        when(userRepository.findByNickname(nickname))
                .thenReturn(Optional.of(user));

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        //when
        List<Map<String,Object>> result = cartService.addCart(cartRequestDto, userDetails);
        Map mapObject = objectMapper.convertValue(result.get(0).get("cart"),Map.class);
        Long countAssert = (Long)mapObject.get("count");

        //then
        assertEquals(count,countAssert);
    }
    @Test
    void readCart() {
    }

    @Test
    void editCart() {
    }

    @Test
    void deleteCart() {
    }
}
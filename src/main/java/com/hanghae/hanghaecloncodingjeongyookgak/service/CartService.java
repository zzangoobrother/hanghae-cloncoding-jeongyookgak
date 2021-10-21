package com.hanghae.hanghaecloncodingjeongyookgak.service;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.CartRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.dto.CartResponseDto;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.ErrorCode;
import com.hanghae.hanghaecloncodingjeongyookgak.exception.HanghaeClonException;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Cart;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;

import com.hanghae.hanghaecloncodingjeongyookgak.model.User;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.CartRepository;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.ProductRepository;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.UserRepository;
import com.hanghae.hanghaecloncodingjeongyookgak.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Map<String,Object>> addCart(CartRequestDto cartRequestDto,UserDetailsImpl userDetails) {
        Long productId = cartRequestDto.getProductId();
        Long cartCount = cartRequestDto.getCount();
        String nickname = userDetails.getUser().getNickname();

        User user = userRepository.findByNickname(nickname). orElseThrow(()->
                new IllegalArgumentException("올바른 아이디가 아닙니다."));
        Product product = productRepository.findById(productId). orElseThrow(() ->
                new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND));

        Cart cart = new Cart(cartCount,product,user);
        Cart findCart = cartRepository.findByProductIdAndUserId(productId, user.getId()).orElse(null);
        if (findCart != null) {
            cartCount= findCart.getCartCount() + cart.getCartCount();
            findCart.setCartCount(cartCount);

        }
        else {
            findCart = cart;
        }
        cartRepository.save(findCart);


        Long totalPrice = cartCount * product.getPrice();

        CartResponseDto cartResponseDto = new CartResponseDto(
                findCart.getId(),
                findCart.getProduct().getTitle(),
                findCart.getProduct().getPrice(),
                findCart.getProduct().getImage(),
                cartCount,
                cartCount*cart.getProduct().getPrice()
        );

        List<Map<String, Object>> addObject = new ArrayList<>();
        Map<String,Object> responseDtoMap = new HashMap<String ,Object>();
        Map<String,Object> responsePriceMap = new HashMap<String ,Object>();
        Map<String,Object> responseMessage  = new HashMap<String ,Object>();

        responseDtoMap.put("cart",cartResponseDto);
        responsePriceMap.put("totalPrice", totalPrice);
        responseMessage.put("result","success");


        addObject.add(responseDtoMap);
        addObject.add(responsePriceMap);
        addObject.add(responseMessage);

        return addObject;
    }



    public List<Map<String, Object>> readCart(UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getNickname();;
        List<Cart> carts = cartRepository.findAllByUserNickname(username);
        List<CartResponseDto> cartResponseDtos = new ArrayList<>();
        Long totalPrice = 0L;

        for (Cart cart : carts) {
            CartResponseDto cartResponseDto = new CartResponseDto(
                    cart.getProduct().getId(),
                    cart.getProduct().getTitle(),
                    cart.getProduct().getPrice(),
                    cart.getProduct().getImage(),
                    cart.getCartCount(),
                    cart.getCartCount()*cart.getProduct().getPrice()
            );

            Long price = cart.getProduct().getPrice() * cart.getCartCount();
            totalPrice += price;
            cartResponseDtos.add(cartResponseDto);
        }

        List<Map<String,Object>> readObject = new ArrayList<>();
        Map<String,Object> responseDtoMap = new HashMap<String ,Object>();
        Map<String,Object> responsePriceMap = new HashMap<String ,Object>();
        Map<String,Object> responseMessage = new HashMap<String ,Object>();

        responseDtoMap.put("carts", cartResponseDtos);
        responsePriceMap.put("totalPrice", totalPrice);
        responseMessage.put("result","success");
        readObject.add(responsePriceMap);
        readObject.add(responseDtoMap);
        readObject.add(responseMessage);

        return readObject;

    }

    @Transactional
    public List<Map<String, Object>> editCart(CartRequestDto cartRequestDto, UserDetailsImpl userDetails) {
        Long productId = cartRequestDto.getProductId();
        Long cartCount = cartRequestDto.getCount();
        Product product = productRepository.findById(productId). orElseThrow(() ->
                new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND));

        Long totalPrice = cartCount * product.getPrice();

        Cart cart = cartRepository.findByProductIdAndUserId(productId,userDetails.getUser().getId()) . orElseThrow(() ->
                new HanghaeClonException(ErrorCode.CART_NOT_FOUND));
        ;
        cart.setCartCount(cartCount);
        cartRepository.save(cart);

        List<Map<String,Object>> editObject = new ArrayList<>();
        Map<String,Object> responseTotalPrice = new HashMap<String ,Object>();
        Map<String,Object> successMessage = new HashMap<>();
        Map<String,Object> responseMessage  = new HashMap<String ,Object>();
        successMessage.put("result", "success");
        responseTotalPrice.put("totalPrice", totalPrice);
        editObject.add(responseTotalPrice);
        editObject.add(successMessage);
        editObject.add(responseMessage);

        return editObject;
    }


    @Transactional
    public Map<String, String> deleteCart(Long productId ,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        cartRepository.deleteByProductIdAndUserId(productId,user.getId());

        Map<String,String> deleteObject = new HashMap<>();
        deleteObject.put("result", "success");

        return deleteObject;
    }

}

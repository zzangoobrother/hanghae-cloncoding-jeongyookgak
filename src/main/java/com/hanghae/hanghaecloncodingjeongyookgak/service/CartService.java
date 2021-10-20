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
import com.hanghae.hanghaecloncodingjeongyookgak.security.UserDetailsImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
}

    @Transactional
    public List<Map<String,Object>> addCart(CartRequestDto cartRequestDto, UserDetailsImpl userDetails) {
    Long productId = cartRequestDto.getProductId();
    Long cartCount = cartRequestDto.getCount();
    User user = userDetails.getUser();

    Product product = productRepository.findById(productId). orElseThrow(() ->
                    new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND));

    Cart cart = new Cart(cartCount,product,user);

    Cart findCart = cartRepository.findByProductId(productId).orElse(null);
        if (findCart != null) {
            cartCount= findCart.getCartCount() + cart.getCartCount();
            cart.setCartCount(cartCount);

        }
        cartRepository.save(cart);

        Long totalPrice = cartCount * product.getPrice();

        Cart responseCart = cartRepository.findById(productId).orElseThrow(()
        -> new HanghaeClonException(ErrorCode.CART_NOT_FOUND));

        CartResponseDto cartResponseDto = new CartResponseDto(
                responseCart.getId(),
                responseCart.getProduct().getTitle(),
                responseCart.getProduct().getPrice(),
                responseCart.getProduct().getImage()
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

        String username = userDetails.getUser().getNickname();
        List<Cart> carts = cartRepository.findAllByUserNickname(username);
        List<CartResponseDto> cartResponseDtos = new ArrayList<>();
        Long totalPrice = 0L;

        for (Cart cart : carts) {
            CartResponseDto cartResponseDto = new CartResponseDto(
                    cart.getProduct().getId(),
                    cart.getProduct().getTitle(),
                    cart.getProduct().getPrice(),
                    cart.getProduct().getImage()
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
    public List<Map<String, Object>> editCart(CartRequestDto cartRequestDto) {
        Long productId = cartRequestDto.getProductId();
        Long cartCount = cartRequestDto.getCount();
        Product product = productRepository.findById(productId). orElseThrow(() ->
                new HanghaeClonException(ErrorCode.PRODUCT_NOT_FOUND));

        Long totalPrice = cartCount * product.getPrice();

        Cart cart = cartRepository.findByProductId(productId) . orElseThrow(() ->
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


    public Map<String, String> deleteCart(CartRequestDto cartRequestDto) {
        Long productId = cartRequestDto.getProductId();
        cartRepository.deleteByProductId(productId);

        Map<String,String> deleteObject = new HashMap<>();
        deleteObject.put("result", "success");

        return deleteObject;
    }
}

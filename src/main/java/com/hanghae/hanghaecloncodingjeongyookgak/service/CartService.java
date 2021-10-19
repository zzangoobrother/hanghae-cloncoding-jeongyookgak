package com.hanghae.hanghaecloncodingjeongyookgak.service;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.CartRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.dto.CartResponseDto;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Cart;
import com.hanghae.hanghaecloncodingjeongyookgak.model.Product;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.CartRepository;
import com.hanghae.hanghaecloncodingjeongyookgak.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository){

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
}


    public List<Map<String,Object>> addCart(CartRequestDto cartRequestDto) {
    Long productId = cartRequestDto.getProductId();
    Long cartCount = cartRequestDto.getCount();

    Product product = productRepository.findById(productId). orElseThrow(() ->
        new IllegalArgumentException("게시물이 존재하지 않습니다."));
    Long totalPrice = cartRequestDto.getCount() * product.getPrice();

    Cart cart = new Cart(product,cartCount);
      CartResponseDto cartResponseDto = new CartResponseDto(
                cart.getId(),
                cart.getProduct().getTitle(),
                cart.getProduct().getPrice(),
                cart.getProduct().getImage()
        );

      List<Map<String, Object>> addObject = new ArrayList<>();
      Map<String,Object> responseDtoMap = new HashMap<String ,Object>();
      Map<String,Object> responsePriceMap = new HashMap<String ,Object>();
      Map<String,Object> result  = new HashMap<String ,Object>();

      responseDtoMap.put("cart",cartResponseDto);
      responsePriceMap.put("totalPrice", totalPrice);
      result.put("result", "success");

        addObject.add(responseDtoMap);
        addObject.add(responsePriceMap);
        addObject.add(result);

        return addObject;
    }



    public List<Map<String, Object>> readCart(UserdetailsImpl userDetails) {

        username = userDetails.getUser().getUsername;
        List<Product> products = productRepository.findByUsername(username);
        List<Map<String, Object>> responseObject = new ArrayList<>();

        Long totalPrice = products
                .stream()
                .mapToLong(Product::getPrice)
                .sum()
                ;

        List<Map<String,Object>> readObject = new ArrayList<>();
        Map<String,Object> responseDtoMap = new HashMap<String ,Object>();
        Map<String,Object> responsePriceMap = new HashMap<String ,Object>();

        responseDtoMap.put("products", products);
        responsePriceMap.put("totalPrice", totalPrice);
        readObject.add(responsePriceMap);
        readObject.add(responseDtoMap);

        return readObject;

    }

    public List<Map<String, Object>> editCart(CartRequestDto cartRequestDto) {
        Long productId = cartRequestDto.getProductId();
        Long cartCount = cartRequestDto.getCount();

        Cart cart = cartRepository.findByProductId(productId);
        cart.setCartCount(cartCount);
        cartRepository.save(cart);
    }


    public Map<String, String> deleteCart(CartRequestDto cartRequestDto) {
        Long productId = cartRequestDto.getProductId();
        cartRepository.deleteByProductId(productId);

        Map<String,String> deleteObject = new HashMap<>();
        deleteObject.put("result", "success");

        return deleteObject;
    }
}

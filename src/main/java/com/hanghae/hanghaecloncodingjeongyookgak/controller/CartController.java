package com.hanghae.hanghaecloncodingjeongyookgak.controller;

import com.hanghae.hanghaecloncodingjeongyookgak.dto.CartRequestDto;
import com.hanghae.hanghaecloncodingjeongyookgak.security.UserDetailsImpl;
import com.hanghae.hanghaecloncodingjeongyookgak.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CartController {

    public CartService cartService;
    public CartController(CartService cartService)
    {
        this.cartService = cartService;
    }


    @PostMapping("/api/cart")
    public List<Map<String, Object>> addCart(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CartRequestDto cartRequestDto){

        return cartService.addCart(cartRequestDto, userDetails);
    }

    @GetMapping("/api/cart")

    public List<Map<String,Object>> readCart(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return cartService.readCart(userDetails);
    }


    @PutMapping("/api/cart")

    public List<Map<String,Object>> editCart(@RequestBody CartRequestDto cartRequestDto){
        return cartService.editCart(cartRequestDto);
    }

    @DeleteMapping("/api/cart")

    public Map<String,String> deleteCart(@RequestBody CartRequestDto cartRequestDto){
        return cartService.deleteCart(cartRequestDto);
    }





}
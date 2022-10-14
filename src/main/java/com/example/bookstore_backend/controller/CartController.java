package com.example.bookstore_backend.controller;
import com.example.bookstore_backend.dto.ResponseDTO;
import com.example.bookstore_backend.dto.CartDTO;
import com.example.bookstore_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@CrossOrigin("*")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody CartDTO cartDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Add record  Success", cartService.addToCart(cartDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get/{user_Id}")
    public ResponseEntity<ResponseDTO> getAll(@PathVariable long user_Id){
        ResponseDTO respnseDTO = new ResponseDTO("Here are all the Cart Items.." , cartService.getCartItems(user_Id));
        return new ResponseEntity<ResponseDTO>(respnseDTO,HttpStatus.OK);
    }
    @PutMapping("/update/{cartId}/{quantity}/{totalPrice}")
    public ResponseEntity<ResponseDTO> updateQuantity( @PathVariable long cartId,@PathVariable int quantity,@PathVariable double totalPrice){
        //System.out.println(cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("updating the Quantity and Price",cartService.updateQuantity(cartId,quantity,totalPrice));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.ACCEPTED);
    }
    @PutMapping("/update/{cartId}")
    public ResponseEntity<ResponseDTO> updateQuantity( @PathVariable long cartId,@RequestBody CartDTO cartDTO){
        System.out.println(cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("updating  all the cart Items",cartService.update(cartDTO,cartId));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<ResponseDTO> removeFromCart(@PathVariable long cartId){
        ResponseDTO responseDTO = new ResponseDTO("Here are all the Cart Items..." , cartService.removeById(cartId));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
    @DeleteMapping("/empty")
    public ResponseEntity<ResponseDTO> emptyCart(){
        ResponseDTO responseDTO = new ResponseDTO("Deleting all the cart Itmens",cartService.emptyCart());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.ACCEPTED);
    }
}

package com.example.bookstore_backend.service;
import com.example.bookstore_backend.dto.CartDTO;
import com.example.bookstore_backend.exception.BookException;
import com.example.bookstore_backend.model.Book;
import com.example.bookstore_backend.model.Cart;
import com.example.bookstore_backend.model.User;
import com.example.bookstore_backend.repository.BookRepo;
import com.example.bookstore_backend.repository.CartRepo;
import com.example.bookstore_backend.repository.UserRepo;
import com.example.bookstore_backend.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    IUserService iUserService;
    @Autowired
    BookService bookService;

    @Autowired
    EmailService emailService;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    UserService userService;


    public Object addToCart(CartDTO cartDTO) {
        Optional<User> userData = userRepo.findById(cartDTO.getUser_Id());
        Optional<Book> bookData = Optional.ofNullable(bookService.getBookById(cartDTO.getBookId()));
        if (userData.isPresent() && bookData.isPresent()) {
            if (bookData.get().bookQuantity >= cartDTO.getQuantity() && cartDTO.getQuantity() > 0) {
                Cart cart = cartRepo.findCartsByUserIdAndBookId(cartDTO.getBookId(), userData.get().getUser_Id());
                if (cart != null) {
                    Cart CartDetails = update(cartDTO, cart.getCartId());
                    return CartDetails;
                } else {
                    double totalPrice = cartDTO.getQuantity() * bookData.get().getPrice();
                    Cart cartDetails = new Cart(userData.get(), bookData.get(), cartDTO.getQuantity(), totalPrice);
                    return cartRepo.save(cartDetails);
                }
            }
            throw (new BookException("Book Out Of Stock"));
        }
        throw (new BookException("Record not Found"));
    }


    public Cart update(CartDTO cartDTO, long id) {
        User userData = userService.getUserById((cartDTO.getUser_Id()));
        if (cartRepo.findById(id).isPresent() && userData != null) {
            Cart cart = cartRepo.findById(id).get();
            cart.setQuantity(cartDTO.quantity);
            cart.setTotalPrice(cart.getQuantity() * cart.getBook().getPrice());
            return cartRepo.save(cart);
        } else throw new BookException("No book found with the given id or you are not an admin user..");
    }

    public Cart updateQuantity(long cartId, int quantity, double totalPrice ) {
       // User userData = userService.getUserById((cartDTO.getUser_Id()));
        if (cartRepo.findById(cartId).isPresent()){
            Cart cart = cartRepo.findById(cartId).get();
            cart.setQuantity(quantity);
            cart.setTotalPrice(totalPrice);
            return cartRepo.save(cart);
        } else throw new BookException("No book found with the given id or you are not an admin user..");
    }

    public List<Cart> getCartItems(long id) {
        return  cartRepo.findCartsByUserId(id);
    }

    public Object removeById(Long id) {
        Optional<Cart> cart = cartRepo.findById(id);
        if (cart.isPresent()){
            cartRepo.delete(cart.get());
            return "Record is deleted with id ";
        }
        throw (new BookException("Record not Found"));
    }
    public String emptyCart() {
        cartRepo.deleteAll();
        return "All Cart Item Deleted";
    }
}
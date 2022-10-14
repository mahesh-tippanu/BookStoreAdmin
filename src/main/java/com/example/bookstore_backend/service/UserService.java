package com.example.bookstore_backend.service;

import com.example.bookstore_backend.dto.LoginDTO;
import com.example.bookstore_backend.dto.UserDTO;
import com.example.bookstore_backend.exception.UserRegistrationException;
import com.example.bookstore_backend.model.User;
import com.example.bookstore_backend.repository.UserRepo;
import com.example.bookstore_backend.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class UserService implements IUserService{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    UserRepo userRepo;
   @Autowired
   EmailService emailService;
    @Override
    public String loginUser(LoginDTO loginDTO) {
        User userLogin = userRepo.findByEmailId(loginDTO.emailId);
        if (userLogin == null)
            return "User not found";
        if (!(bCryptPasswordEncoder.matches(loginDTO.password, userLogin.getPassword())))
            return "User name or password incorrect";
        String token = tokenUtil.createToken(userLogin.getUser_Id());
        log.info("LoginUser Service Method Successfully executed");
        return token;
    }
    @Override
    public User registerUser(UserDTO userDTO) {
        String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        User user = new User(encodedPassword,userDTO.userName,userDTO.emailId);
         userRepo.save(user);
        emailService.sendmail(user.getEmailId());
        return user;
    }
    @Override
    public List<User> getUserInfo() {
        log.info("getUserInfo Service Method Successfully executed");
        return userRepo.findAll() ;
    }
    public User getUserById(long user_Id) {
        return userRepo.findById(user_Id)
                .orElseThrow(() -> new UserRegistrationException(" Book store  Contact Id not Found!!!"));
    }
    public User editUserData(long user_Id, UserDTO userDTO) {
        User user = this.getUserById(user_Id);
        user.updateData(userDTO);
        return userRepo.save(user);
    }
    public void deleteUserData(long bookId) {
        User user= this.getUserById(bookId);
        userRepo.delete(user);
    }
}

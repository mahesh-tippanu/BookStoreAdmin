package com.example.bookstore_backend.controller;
import com.example.bookstore_backend.dto.BookDTO;
import com.example.bookstore_backend.dto.LoginDTO;
import com.example.bookstore_backend.dto.ResponseDTO;
import com.example.bookstore_backend.dto.UserDTO;
import com.example.bookstore_backend.model.Book;
import com.example.bookstore_backend.model.User;
import com.example.bookstore_backend.service.EmailService;
import com.example.bookstore_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.List;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserService IUserService;

    @Autowired
    EmailService emailService;

    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        //log.info("inside userLogin Controller Method");
        ResponseDTO responseDTO = new ResponseDTO("User Logged In Successfully!!!", IUserService.loginUser(loginDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


      @PostMapping(value = {"/register"})
      public   ResponseEntity<ResponseDTO>  registerUser(@RequestBody com.example.bookstore_backend.dto.UserDTO userDTO) {
       User user = null;
       user =IUserService.registerUser(userDTO );
        ResponseDTO responseDTO = new ResponseDTO("User Registered Successfully!!!", user);
        sendmail(user);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    public  void sendmail(User user) {

        final String emailToRecipient = user.getEmailId();
        final String emailSubject = "Successfully Email send by sender";
        final String emailMessage = "Registered Successfully.. ";

        javaMailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(emailToRecipient);
                mimeMessageHelper.setText(emailMessage, true);
                mimeMessageHelper.setSubject(emailSubject);
                System.out.println("mailsend");
            }
        });
    }




    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getUserInfo() {
        List<User> userList = IUserService.getUserInfo();
        ResponseDTO responseDTO = new ResponseDTO("Getting User Info ", userList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = {"/get/{user_Id}"})
    public ResponseEntity<ResponseDTO> getUserData(@PathVariable int user_Id) {
        User user= IUserService.getUserById(user_Id);
        ResponseDTO responseDTO = new ResponseDTO("Success Call for Person Id!!!", user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(value = {"/edit/{user_Id}"})
    public ResponseEntity<ResponseDTO> editUserData(@PathVariable int user_Id,
                                                    @RequestBody UserDTO userDTO) {
        User user = IUserService.editUserData(user_Id, userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Data UPDATED Successfully!!!", user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping(value = {"/remove/{user_Id}"})
    public ResponseEntity<ResponseDTO> removeUserData(@PathVariable int user_Id) {
        userService.deleteUserData(user_Id);
        ResponseDTO responseDTO = new ResponseDTO("Data DELETED Successfully!!!",
                "ID number: " + user_Id + " --> DELETED!!!");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
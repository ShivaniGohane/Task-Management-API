package com.Task_Manageent_API.Project1.Controller;

import com.Task_Manageent_API.Project1.DTO.response.GeneralMessageDTO;
import com.Task_Manageent_API.Project1.Exceptionss.UserNotExistException;
import com.Task_Manageent_API.Project1.Exceptionss.WrongCredentialException;
import com.Task_Manageent_API.Project1.Model.User1;
import com.Task_Manageent_API.Project1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    // SignUp API for user sign up user need to provide Email and Password and token if token is "ADMIN_ACCESS" status will be ADMINRole otherwise it will be NORMAL_USERRole
    @PostMapping("/SignUp")
    public ResponseEntity SignUp(@RequestParam("Email")String email, @RequestParam("Password")String password, @RequestParam("token") String Token){
        User1 user1 = userService.SignUp(email, password, Token);
       return new ResponseEntity(user1, HttpStatus.CREATED);
    }

    // Login API user can log in with Email and Password if user is not existed in database user need to signup first, if user entered wrong password it will show "Wrong Password".
    @GetMapping("/login")
    public ResponseEntity login(@RequestParam("Email")String email, @RequestParam("Password")String password){
        try {
            User1 user1 = userService.Login(email, password);
            return new ResponseEntity(user1, HttpStatus.OK);
        }catch (UserNotExistException userNotExistException){
            return new ResponseEntity(new GeneralMessageDTO(userNotExistException.getMessage()), HttpStatus.NOT_FOUND);
        }catch (WrongCredentialException wrongCredentialException){
            return new ResponseEntity(new GeneralMessageDTO(wrongCredentialException.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}

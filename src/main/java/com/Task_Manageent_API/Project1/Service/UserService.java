package com.Task_Manageent_API.Project1.Service;

import com.Task_Manageent_API.Project1.Enum.Role;
import com.Task_Manageent_API.Project1.Exceptionss.UserNotExistException;
import com.Task_Manageent_API.Project1.Exceptionss.WrongCredentialException;
import com.Task_Manageent_API.Project1.Model.Task;
import com.Task_Manageent_API.Project1.Model.User1;
import com.Task_Manageent_API.Project1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository2;

    //SignUp Method if token "ADMIN_ACCESS" status will ADMINRole otherwise it will be USERRole
    public User1 SignUp(String Email, String Password, String token){
        User1 user1 = new User1();
        user1.setEmail(Email);
        user1.setPassword(Password);
        if(token.equals("ADMIN_ACCESS")){
            user1.setRole(Role.AdminRole);
        }
        else {
            user1.setRole(Role.UserRole);
        }
        userRepository2.save(user1);
        return user1;
    }

    //Login Method if user not exist or password is incorrect it will throw exception
    public User1 Login(String Email, String Password){
        User1 user1 = userRepository2.getUserByEmail(Email);
        if(user1==null){
            throw new UserNotExistException("User Not Exist, Please SignUp.");
        }
        if(!user1.getPassword().equals(Password)){
            throw new WrongCredentialException("Password Incorrect.");
        }
        return user1;
    }

    public void inserUser_Task_Table(UUID userId, UUID taskId){
        userRepository2.insertUser_Task_List(userId, taskId);
    }

    public void updateUser_Task_List(UUID userId, UUID taskId){
        userRepository2.updateUser_Task_List(userId, taskId);
    }

    public User1 getUserByMail(String Email){
        return userRepository2.getUserByEmail(Email);
    }

    public User1 getUserByUserId(UUID userid){
        return userRepository2.getUserByUserId(userid);
    }

    public UUID getUserByTaskId(UUID taskId){
        return userRepository2.getUserByTaskId(taskId);
    }

    public void delteFromUser_Task_List(UUID taskId){
        userRepository2.delteFromUserTaskList(taskId);
    }

    public Task isPresent(UUID taskId){
        return userRepository2.isPresent(taskId);
    }
}

package com.Task_Manageent_API.Project1;

import com.Task_Manageent_API.Project1.Enum.Role;
import lombok.*;

//Dummy User class to create dummy user object.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User2 {
    private String Email;
    private String Password;

    private Role role;
}

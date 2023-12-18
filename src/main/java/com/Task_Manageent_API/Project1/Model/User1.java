package com.Task_Manageent_API.Project1.Model;

import com.Task_Manageent_API.Project1.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uId;

    @Column(unique = true)
    private String Email;

    private String Password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Task> taskList = new ArrayList<>();
}

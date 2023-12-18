package com.Task_Manageent_API.Project1;

import com.Task_Manageent_API.Project1.Enum.Status;
import lombok.*;

import java.sql.Date;

//Dummy Task class to create dummy object.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task2 {
    private String title;

    private String description;

    private Date date;

    private Status status;
}

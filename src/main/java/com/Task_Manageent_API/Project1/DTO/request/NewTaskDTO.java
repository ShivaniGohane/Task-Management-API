package com.Task_Manageent_API.Project1.DTO.request;

import com.Task_Manageent_API.Project1.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NewTaskDTO {

    private String title;

    private String description;

    private Date date;

    private Status status;
}

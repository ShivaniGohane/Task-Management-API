package com.Task_Manageent_API.Project1.DTO.request;

import com.Task_Manageent_API.Project1.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
@ToString
public class TaskDTO {
    private UUID prevId;
    private String title;
    private String description;
    private Date date;
    private String Email;
}

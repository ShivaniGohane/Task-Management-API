package com.Task_Manageent_API.Project1.Repository;

import com.Task_Manageent_API.Project1.Enum.Status;
import com.Task_Manageent_API.Project1.Model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(value = "select * from task where title like %:task_title%", nativeQuery = true)
    public Page<Task> getTaskListFilteringByTitle(String task_title, Pageable pageable);


    @Query(value = "select * from task where id=:taskId", nativeQuery = true)
    public Task getTaskById(UUID taskId);


}

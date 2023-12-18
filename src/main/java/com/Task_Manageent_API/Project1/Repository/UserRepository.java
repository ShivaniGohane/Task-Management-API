package com.Task_Manageent_API.Project1.Repository;

import com.Task_Manageent_API.Project1.Enum.Role;
import com.Task_Manageent_API.Project1.Model.Task;
import com.Task_Manageent_API.Project1.Model.User1;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.instrument.classloading.ResourceOverridingShadowingClassLoader;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User1, UUID> {

    @Query(value = "select * from user1 where email=:Email" , nativeQuery = true)
    public User1 getUserByEmail(String Email);

    @Query(value = "select * from user1 where u_id=:userId" , nativeQuery = true)
    public User1 getUserByUserId(UUID userId);

    @Modifying
    @Transactional
    @Query(value = "insert into user1_task_list VALUES (:userId, :taskId)", nativeQuery = true)
    public void insertUser_Task_List(UUID userId, UUID taskId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user1_task_list SET user1_u_id=:userId WHERE task_list_id=:taskId", nativeQuery = true)
    public void updateUser_Task_List(UUID userId, UUID taskId);

    @Query(value = "select user1_u_id from user1_task_list where task_list_id=:taskId", nativeQuery = true)
    public UUID getUserByTaskId(UUID taskId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user1_task_list WHERE task_list_id=:taskId", nativeQuery = true)
    public void delteFromUserTaskList(UUID taskId);


    @Query(value = "SELECT * FROM user1_task_list WHERE task_list_id=:taskId", nativeQuery = true)
    public Task isPresent(UUID taskId);

}

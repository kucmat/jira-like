package com.demo.app.Repository;

import com.demo.app.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {



    List<Task> findTasksByAssignedTo(String userName);

    List<Task> findTasksByCreatedBy(String userName);

    //List<Task> findTasksByUserName(String userName);

}

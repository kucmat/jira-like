package com.demo.app.Controller;

import com.demo.app.Entity.Task;
import com.demo.app.Entity.User;
import com.demo.app.Repository.TaskRepository;
import com.demo.app.Repository.UserRepository;
import com.demo.app.Service.SendNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    SendNotificationService sender;

    @GetMapping("/allTasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        System.out.println("getAllTasks");
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("for user: " + userDetails.getUsername());
        String userName = userDetails.getUsername();
        try{
            List<Task> tasks = new ArrayList<>();

            taskRepository.findTasksByAssignedTo(userName).forEach(tasks::add);
            taskRepository.findTasksByCreatedBy(userName).forEach(tasks::add);

            if (tasks.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }catch(Exception e){

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/allTasksCreated")
    public ResponseEntity<List<Task>> getAllTasksCreated(){
        System.out.println("getAllTasksCreated");
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("for user: " + userDetails.getUsername());
        String userName = userDetails.getUsername();
        try{
            List<Task> tasks = new ArrayList<>();

            taskRepository.findTasksByCreatedBy(userName).forEach(tasks::add);

            if (tasks.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }catch(Exception e){

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/allTasksAssigned")
    public ResponseEntity<List<Task>> getAllTasksAssigned(){
        System.out.println("getAllTasksAssigned");
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("for user: " + userDetails.getUsername());
        String userName = userDetails.getUsername();
        try{
            List<Task> tasks = new ArrayList<>();

            taskRepository.findTasksByAssignedTo(userName).forEach(tasks::add);

            if (tasks.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            sender.SendSMS(userName);
            sender.SendEmail(userName);
            System.out.println("success");
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }catch(Exception e){

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/createTask")
    public ResponseEntity<String> createNewTask(@RequestBody Task task){
        System.out.println("createNewTask");
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("by user: " + userDetails.getUsername());
        String createdBy = userDetails.getUsername();
        try{
            List<Task> tasks = new ArrayList<>();

            taskRepository.save(new Task(task.getName(), task.getDescription(), task.getPriority(), createdBy, task.getAssignedTo(), new Date()));

            return new ResponseEntity<>("Task was created successfully.", HttpStatus.CREATED);

        }catch(Exception e){

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }






}

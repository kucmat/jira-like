package com.demo.app.Entity;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Task")
@Table(
        name = "task"
)
public class Task {

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "task_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "description",
            nullable = false
    )
    private String description;
    @Column(
            name = "priority",
            nullable = false
    )
    private String priority;
    @Column(
            name = "created_by",
            nullable = true
    )
    private String createdBy;
    @Column(
            name = "assigned_to",
            nullable = false
    )
    private String assignedTo;
    @Column(
            name = "date_created",
            nullable = true
    )
    private Date dateCreated;

    public Task(String name, String description, String priority, String createdBy, String assignedTo, Date dateCreated) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.dateCreated = dateCreated;
    }

    public Task() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCreatedBy() { return createdBy; }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", createdBy=" + createdBy +
                ", assignedTo=" + assignedTo +
                ", dateCreated=" + dateCreated +
                '}';
    }
}

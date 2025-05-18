package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Task implements Serializable {
    private final UUID _id = UUID.randomUUID();
    private String title;
    private LocalDate dueDate;
    private boolean completed;
    private String priority;
    private String description;
    private LocalDate createdAt;

    public Task(String title, LocalDate dueDate, String priority) {
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
    }

    public Task(String title, String description, String priority, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false;
        this.dueDate = dueDate;
        this.createdAt = LocalDate.now();
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return (completed ? "[X] " : "[ ] ") + title +
                " (Due: " + dueDate + ", Priority: " + priority + ")";
    }

    public UUID getId() {
        return this._id;
    }

    public void setDescription(String text) {
        this.description = text;
    }

    public void markAsCompleted() {
        this.completed = true;
    }
}
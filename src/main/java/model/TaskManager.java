package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    // Add a new task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Remove a task
    public void removeTask(UUID taskID) {
        tasks.removeIf(task -> task.getId().equals(taskID));
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return tasks;
    }

    // Get only completed tasks
    public List<Task> getCompletedTasks() {
        return tasks.stream()
                .filter(Task::isCompleted)
                .collect(Collectors.toList());
    }

    // Get only pending tasks
    public List<Task> getPendingTasks() {
        return tasks.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
    }

    // Get tasks due before a certain date
    public List<Task> getTasksDueBefore(LocalDate date) {
        return tasks.stream()
                .filter(task -> task.getDueDate().isBefore(date))
                .collect(Collectors.toList());
    }

    // Mark a task as completed
    public void completeTask(Task task) {
        task.setCompleted(true);
    }

    // Clear all completed tasks
    public void clearCompletedTasks() {
        tasks.removeIf(Task::isCompleted);
    }
}
import java.time.LocalDate;

import model.Task;

public class TaskTestManual {

    public static void main(String[] args) {
        TaskTestManual test = new TaskTestManual();
        test.runAllTests();
    }

    private void runAllTests() {
        testTaskCreation();
        testTaskCompletion();
        testTaskDescription();
    }

    private void testTaskCreation() {
        Task task = new Task("Test task", "This is a test task", "Medium", LocalDate.now());

        if (!"This is a test task".equals(task.getDescription())) {
            System.out.println("❌ testTaskCreation: Description failed");
        } else if (task.isCompleted()) {
            System.out.println("❌ testTaskCreation: Task should not be completed");
        } else {
            System.out.println("✅ testTaskCreation passed");
        }
    }

    private void testTaskCompletion() {
        Task task = new Task("Test task", "This is a test task", "Medium", LocalDate.now());
        task.markAsCompleted();

        if (!task.isCompleted()) {
            System.out.println("❌ testTaskCompletion: Task should be marked as completed");
        } else {
            System.out.println("✅ testTaskCompletion passed");
        }
    }

    private void testTaskDescription() {
        Task task = new Task("Test task", "This is a test task", "Medium", LocalDate.now());
        task.setDescription("Updated task");

        if (!"Updated task".equals(task.getDescription())) {
            System.out.println("❌ testTaskDescription: Description not updated");
        } else {
            System.out.println("✅ testTaskDescription passed");
        }
    }
}
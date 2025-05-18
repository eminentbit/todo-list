import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Task;

public class TaskTest {
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Test task", "This is a test task", "Medium", LocalDate.now());
    }

    @Test
    void testTaskCreation() {
        assertEquals("This is a test task", task.getDescription());
        assertFalse(task.isCompleted());
    }

    @Test
    void testTaskCompletion() {
        task.markAsCompleted();
        assertTrue(task.isCompleted());
    }

    @Test
    void testTaskDescription() {
        task.setDescription("Updated task");
        assertEquals("Updated task", task.getDescription());
    }
}
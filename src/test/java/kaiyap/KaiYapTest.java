package kaiyap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link KaiYap}, focusing on task management functionalities.
 * It verifies the ability to add, delete, and find tasks within the application.
 */
class KaiYapTest {

    private KaiYap kaiYap;
    @BeforeEach
    void setUp() throws IOException {
        kaiYap = new KaiYap();
    }

    @Test
    void testAddingTask() {
        // Simulate adding a task and expect a success message or task count to increase.
        String taskDescription = "Read a book";
        kaiYap.chatResponse("todo " + taskDescription); // Assuming chatResponse handles adding tasks.
        System.out.println(kaiYap.getTaskList().size());
        assertTrue(kaiYap.getTaskList().contains(taskDescription), "Task list should contain the added task.");
    }

    @Test
    void testDeletingTask() {
        // Add a task, then delete it, and verify it's no longer in the list.
        String taskDescription = "Write JUnit tests";
        kaiYap.chatResponse("todo " + taskDescription);
        assertTrue(kaiYap.getTaskList().contains(taskDescription),
                "Task list should contain the added task before deletion.");

        kaiYap.chatResponse("delete 2"); // Assuming chatResponse can handle deleting tasks.
        assertFalse(kaiYap.getTaskList().contains(taskDescription), "Task list should not contain the deleted task.");
    }

    @Test
    void testFindTask() {
        // Add a couple of tasks and then find a specific one.
        String task1 = "Read a book";
        String task2 = "Write JUnit tests";
        kaiYap.chatResponse("todo " + task1);
        kaiYap.chatResponse("todo " + task2);

        String findResponse = kaiYap.chatResponse("find JUnit"); // Assuming "find" command searches task descriptions.
        assertTrue(findResponse.contains(task2), "Find response should contain the task related to the search term.");
        assertFalse(findResponse.contains(task1), "Find response should not contain unrelated tasks.");
    }
}

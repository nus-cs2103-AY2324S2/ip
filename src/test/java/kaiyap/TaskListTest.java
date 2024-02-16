package kaiyap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        // Assuming a basic constructor for Ui or a mocked/stubbed version.
        // Simplify this according to your actual implementation.
        Ui mockUi = new Ui(); // Adjust this to fit the actual Ui class usage.
        taskList = new TaskList(mockUi);
    }

    @Test
    void testTaskCreatorWithTodo() {
        // Testing creation of a TODO task.
        assertDoesNotThrow(() -> {
            Task task = taskList.taskCreator("todo Read a book");
            assertNotNull(task);
            assertInstanceOf(Todo.class, task);
            assertEquals("Read a book", task.getListItem());
        });
    }

    @Test
    void testFindTasksWithKeyword() {
        // Adding tasks and testing the find functionality.
        taskList.add(new Todo("Read a book", "todo"));
        taskList.add(new Deadline("Submit report", "deadline", LocalDateTime.now()));
        taskList.add(new Event("Team meeting", "event", LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        System.out.println(taskList.size());
        List<Task> foundTasks = taskList.findTasks("report");
        assertEquals(1, foundTasks.size());
        assertTrue(foundTasks.get(0).getListItem().contains("Submit report"));
    }

    @Test
    void testContainsWithExistingWord() {
        // Testing contains method for an existing task description.
        taskList.add(new Todo("Read a book", "todo"));
        assertTrue(taskList.contains("Read a book"));
    }

    @Test
    void testContainsWithNonExistingWord() {
        // Testing contains method for a non-existing task description.
        taskList.add(new Todo("Read a book", "todo"));
        assertFalse(taskList.contains("Write a report"));
    }
}

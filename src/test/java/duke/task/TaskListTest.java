package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.commons.exceptions.DukeException;

public class TaskListTest {
    @Test
    void testDuplicatedTask() throws DukeException {
        TaskList taskList = new TaskList();

        Task todo1 = new ToDo("Read book");
        Task deadline1 = new Deadline("Submit report", LocalDate.of(2024, 4, 1));
        Task event1 = new Event("Project meeting", LocalDate.of(2024, 4, 2), LocalDate.of(2024, 4, 3));

        // Duplicates
        Task todoDuplicate = new ToDo("Read book");
        Task deadlineDuplicate = new Deadline("Submit report", LocalDate.of(2024, 4, 1));
        Task eventDuplicate = new Event("Project meeting", LocalDate.of(2024, 4, 2), LocalDate.of(2024, 4, 3));

        // Non-duplicates
        Task todoNonDuplicate = new ToDo("Write essay");
        Task deadlineNonDuplicate = new Deadline("Finish project", LocalDate.of(2024, 5, 1));
        Task eventNonDuplicate = new Event("Team outing", LocalDate.of(2024, 5, 2), LocalDate.of(2024, 5, 3));

        taskList.addTask(todo1);
        taskList.addTask(deadline1);
        taskList.addTask(event1);
        
        assertEquals(taskList.getNumberTasks(), 3);

        assertTrue(taskList.checkDuplicatedTask(todoDuplicate), "ToDo duplicate should be detected.");
        assertTrue(taskList.checkDuplicatedTask(deadlineDuplicate), "Deadline duplicate should be detected.");
        assertTrue(taskList.checkDuplicatedTask(eventDuplicate),  "Event duplicate should be detected.");

        assertFalse(taskList.checkDuplicatedTask(todoNonDuplicate),
                "ToDo non-duplicate should not be detected as duplicate.");
        assertFalse(taskList.checkDuplicatedTask(deadlineNonDuplicate),
                "taskList.checkDuplicatedTask non-not be detected as duplicate.");
        assertFalse(taskList.checkDuplicatedTask(eventNonDuplicate),
                "Event non-duplicate should not be detected as duplicate.");

        assertThrows(DukeException.class, () -> taskList.addTask(todoDuplicate));
        assertThrows(DukeException.class, () -> taskList.addTask(deadlineDuplicate));
        assertThrows(DukeException.class, () -> taskList.addTask(eventDuplicate));

        assertEquals(taskList.addTask(todoNonDuplicate), todoNonDuplicate.toString());
        assertEquals(taskList.addTask(deadlineNonDuplicate), deadlineNonDuplicate.toString());
        assertEquals(taskList.addTask(eventNonDuplicate), eventNonDuplicate.toString());
    }
}

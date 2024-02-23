package chatbot;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testAddEventWithCorrectFormat() {
        TaskList taskList = new TaskList();
        String result = taskList.addTaskFromInput("event Project meeting /from 12/12/2023 0900 /to 12/12/2023 1200");
        assertEquals("Got it. I've added this task:\n  [E][ ] Project meeting (from: 12/12/2023 09:00 to: 12/12/2023"
                + " 12:00)\nNow you have 1 task in the list.", result);
    }

    @Test
    public void testMarkTaskAsDone() {
        TaskList taskList = new TaskList();
        taskList.addTaskFromInput("todo read book");
        assertDoesNotThrow(() -> {
            String result = taskList.markList(0);
            assertEquals("Nice! I've marked this task as done:\n  [T][X] read book", result);
        });
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        taskList.addTaskFromInput("todo read book");
        assertDoesNotThrow(() -> {
            String result = taskList.deleteList(0);
            assertEquals("Noted. I've removed this task:\n  "
                    + "[T][ ] read book\nNow you have 0 tasks in the list.", result);
        });
    }
    @Test
    public void testFindByDate() {
        TaskList taskList = new TaskList();
        taskList.addTaskFromInput("deadline Return book /by 22/12/2021 1800");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse("22/12/2021 1800", formatter);
        TaskList result = taskList.findByDate(dateTime);
        assertEquals(1, result.taskList.size());
    }

    @Test
    public void testFindByKeyword() {
        TaskList taskList = new TaskList();
        taskList.addTaskFromInput("todo read book");
        TaskList result = taskList.findByKeyword("read");
        assertEquals(1, result.taskList.size());
    }
}


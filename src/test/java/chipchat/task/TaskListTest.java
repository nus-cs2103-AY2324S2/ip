package chipchat.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import chipchat.parser.Parser;

/**
 * Represents a class that tests the functioning of TaskList.
 */
public class TaskListTest {

    /**
     * Tests whether the toString() methods of TaskList and all tasks returns the correct string.
     */
    @Test
    public void toString_returnCorrectListOfString() {
        Task todo = new Todo("study", false);

        LocalDate dueBy = Parser.parseDate("2024-01-01");
        Task deadline = new Deadline("homework", false, dueBy);

        LocalDate dateFrom = Parser.parseDate("2024-01-01");
        LocalDate dateTo = Parser.parseDate("2024-01-02");
        Task event = new Event("trip", false, dateFrom, dateTo);

        ArrayList<Task> taskArrayList = new ArrayList<>(Arrays.asList(todo, deadline, event));
        TaskList tasks = new TaskList(taskArrayList);

        String expectedString = "0. [T][ ] study\n"
                + "1. [D][ ] homework (by: 2024-01-01)\n"
                + "2. [E][ ] trip (from: 2024-01-01 to: 2024-01-02)\n";
        assertEquals(expectedString, tasks.toString());
    }
}

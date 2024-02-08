package chipchat.task;

import chipchat.parser.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
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

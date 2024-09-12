package task; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.TaskList;

public class FindTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testFine() {

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Read book"));

        LocalDate date = LocalDate.of(2022, 2, 2);
        LocalTime time = LocalTime.of(10, 00);

        tasks.add(new Deadline("Return book", date, time));

        LocalDate endDate = LocalDate.of(2022 , 2 , 10);
        LocalTime endTime = LocalTime.of(10, 00);
        tasks.add(new Event("Project Meeting", date, time, endDate, endTime));

        // Create TaskList instance
        TaskList taskList = new TaskList(tasks);

        // Check if the tasks are found successfully
        String result = taskList.find("book");
        assertEquals("buzz buzz~~ Here are the matching tasks in your list:\n"
                + "1.[T][ ] Read book\n"
                + "2.[D][ ] Return book (by: 02 Feb 2022 10:00)\n", result);

        // Show error message when task is not found
        String result2 = taskList.find("friend");
        assertEquals("buzz buzz~~ No task in your list match this result", result2);

    }
}

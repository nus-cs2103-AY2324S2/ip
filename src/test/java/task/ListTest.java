package task; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.TaskList;

public class ListTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testList() {

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Task 1"));

        LocalDate date = LocalDate.of(2022, 2, 2);
        LocalTime time = LocalTime.of(10, 00);
        tasks.add(new Deadline("Deadline 2", date, time));

        LocalDate endDate = LocalDate.of(2022 , 2 , 10);
        LocalTime endTime = LocalTime.of(10, 00);
        tasks.add(new Event("Event 3", date, time, endDate, endTime));

        // Create TaskList instance
        TaskList taskList = new TaskList(tasks);

        String result = taskList.showList();

        // Check if all tasks are listed successfully
        assertEquals("Here are the tasks in your list:\n"
                + "1.[T][ ] Task 1\n"
                + "2.[D][ ] Deadline 2 (by: 02 Feb 2022 10:00)\n"
                + "3.[E][ ] Event 3 (from: 02 Feb 2022 10:00 | to: 10 Feb 2022 10:00)\n", result);

        // Check the size of the list
        assertEquals(3, taskList.getTaskList().size());


    }
}

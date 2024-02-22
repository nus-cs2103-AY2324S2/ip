package task; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.TaskList;

public class UpdateTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testUpdate() {

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

        // Check if the task is updated successfully
        String result = taskList.update(2, "Project Meeting for CS2103T");
        assertEquals("buzz buzz~~ I have updated the description\n"
                + "[E][ ] Project Meeting for CS2103T (from: 02 Feb 2022 10:00"
                + " | to: 10 Feb 2022 10:00)", result);

    }
}

package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Fredricksen.tasks.TaskList;
import org.junit.jupiter.api.Test;
import Fredricksen.tasks.taskType.Task;

public class TaskTest {

    /**
     * Checks if toString method returns string with correct format.
     */
    /** @Test
     public void taskToString_correctFormat() {
        String timeCommand = "2/12/2019 1800";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime time = LocalDateTime.parse(timeCommand, format);
        Task newTask = new Task("deadline homework /by 2/12/2019 1800", "D", false, time);
        assertEquals(newTask.toString(), "[D][] deadline homework /by 2/12/2019 1800");
    } */

    @Test
    public void taskListToString_correctFormat() {
        TaskList newList = new TaskList();
        Task newTask1 = new Task("todo homework", "T", false);
        Task newTask2 = new Task("todo laundry", "T", false);
        Task newTask3 = new Task("todo housework", "T", true);
        newList.addTask(newTask1);
        newList.addTask(newTask2);
        newList.addTask(newTask3);
        assertEquals(newList.toString(),
                "[T][] todo homework\n"
                        + "[T][] todo laundry\n"
                        + "[T][X] todo housework\n");
    }
}

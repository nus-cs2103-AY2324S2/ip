package Bentley;

import java.util.ArrayList;

import Bentley.Task;
import Bentley.Deadline;
import Bentley.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void addUnmarkedDeadline(){
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addDeadlineTask("deadline return book by/ 2025-09-09");

        Task task = taskList.getTasks().get(0);
        assertEquals("0", task.doneOrNot());
    }
}


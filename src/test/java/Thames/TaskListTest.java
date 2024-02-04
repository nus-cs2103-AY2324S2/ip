package Thames;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import Thames.task.Task;
import Thames.task.ToDo;
import Thames.task.Event;
import Thames.task.Deadline;

import java.util.ArrayList;
import java.util.Scanner;


public class TaskListTest {
    @Test
    public void taskListInitTest() {
        Task task1 = new ToDo("return book");
        Task task2 = new Deadline("finish homework", "2024-01-01");
        Task task3 = new Event("vacation", "2024-01-01", "2024-01-10");
        task2.mark();
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(task1);
        expected.add(task2);
        expected.add(task3);

        TaskList result = new TaskList(new Scanner("T, ,return book\n" +
                "D,X,finish homework,2024-01-01\n" +
                "E, ,vacation,2024-01-01,2024-01-10"));
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void taskListAddTest() {
        TaskList result = new TaskList(new Scanner(""));
        assertEquals(0, result.size());

        result.add(new ToDo("return book"));
        assertEquals(1, result.size());

        result.add(new Deadline("finish homework", "2024-01-01"));
        assertEquals(2,result.size());
    }
}

package friendlytool.process;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import friendlytool.task.ToDo;

public class TaskFinderTest {
    @Test
    public void findTaskTest() {
        TaskList list = new TaskList();
        try {
            ToDo task1 = new ToDo("task 1", false);
            ToDo task2 = new ToDo("task 2", false);
            ToDo task3 = new ToDo("task 3", false);
            ToDo assignment = new ToDo("todo assignment1", false);
            list.add(task1);
            list.add(task2);
            list.add(task3);
            list.add(assignment);
            String actual = TaskFinder.findTask(list, "todo task");
            StringBuilder sb = new StringBuilder();
            sb.append("____________________________________________________________\n")
                    .append("These are the matching tasks in your list:\n")
                    .append("    ").append(1).append(".").append(task1).append("\n")
                    .append("    ").append(2).append(".").append(task2).append("\n")
                    .append("    ").append(3).append(".").append(task3).append("\n")
                    .append("____________________________________________________________\n");
            String expected = sb.toString();
            assertEquals(expected, actual);
        } catch (FtException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}

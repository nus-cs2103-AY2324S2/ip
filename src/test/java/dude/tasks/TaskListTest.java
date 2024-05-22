package dude.tasks;

import dude.exceptions.TaskListFullException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testSortingTasks() throws TaskListFullException {
        TaskList taskList = new TaskList();

        LocalDateTime dateTime1 = LocalDateTime.parse("2021-08-25T10:00");
        LocalDateTime dateTime2 = LocalDateTime.parse("2021-08-26T12:00");
        LocalDateTime dateTime3 = LocalDateTime.parse("2021-08-27T10:00");

        Todo todo = new Todo("test");
        Todo todo2 = new Todo("test2");
        Event event = new Event("test", dateTime1, dateTime2);
        Event event2 = new Event("test", dateTime2, dateTime3);
        Deadline deadline1 = new Deadline("test", dateTime1);
        Deadline deadline2 = new Deadline("test", dateTime3);

        taskList.add_task(deadline2);
        taskList.add_task(deadline1);
        taskList.add_task(todo);
        taskList.add_task(event);
        taskList.add_task(event2);
        taskList.add_task(todo2);

        ArrayList<Task> tasksActual = taskList.getList();
        List<Task> t = Arrays.asList(todo, todo2, deadline1, event, event2, deadline2);
        ArrayList<Task> tasksExpected = new ArrayList<>(t);

        System.out.println(taskList.toString());
        assertEquals(tasksExpected, tasksActual);
    }
}

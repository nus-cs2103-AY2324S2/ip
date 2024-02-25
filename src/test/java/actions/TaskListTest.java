package actions;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void listTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        Todo todo = new Todo("read", false);
        tasks.add(todo);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime by = LocalDateTime.parse("2/12/2023 1800", formatter);
        Deadline deadline = new Deadline("read book", false, by );
        tasks.add(deadline);

        LocalDateTime from = LocalDateTime.parse("2/12/2023 1800", formatter);
        LocalDateTime to = LocalDateTime.parse("3/12/2023 1800", formatter);
        Event event = new Event("school", false, from, to );
        tasks.add(event);

        TaskList taskList = new TaskList(tasks);
        assertEquals(tasks, taskList.getTasks());

    }

    @Test
    public void addTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Task> newTasks = new ArrayList<>();

        Todo todo = new Todo("read", false);
        tasks.add(todo);
        newTasks.add(todo);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime by = LocalDateTime.parse("2/12/2023 1800", formatter);
        Deadline deadline = new Deadline("read book", false, by );
        tasks.add(deadline);
        newTasks.add(deadline);

        LocalDateTime from = LocalDateTime.parse("2/12/2023 1800", formatter);
        LocalDateTime to = LocalDateTime.parse("3/12/2023 1800", formatter);
        Event event = new Event("school", false, from, to );
        tasks.add(event);
        newTasks.add(event);

        Todo newTodo = new Todo("eat", false);
        newTasks.add(newTodo);

        TaskList taskList = new TaskList(tasks);
        taskList.addTask(newTodo);
        assertEquals(newTasks, tasks);
    }


    @Test
    public void deleteTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Task> newTasks = new ArrayList<>();

        Todo todo = new Todo("read", false);
        tasks.add(todo);
        newTasks.add(todo);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime by = LocalDateTime.parse("2/12/2023 1800", formatter);
        Deadline deadline = new Deadline("read book", false, by );
        tasks.add(deadline);
        newTasks.add(deadline);

        LocalDateTime from = LocalDateTime.parse("2/12/2023 1800", formatter);
        LocalDateTime to = LocalDateTime.parse("3/12/2023 1800", formatter);
        Event event = new Event("school", false, from, to );
        tasks.add(event);

        TaskList taskList = new TaskList(tasks);
        taskList.deleteTask(2);
        assertEquals(newTasks, tasks);
    }

}

package luke.task;

import luke.exception.DateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void testSize() throws DateException {
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList();
        Todo todo = new Todo("read book");
        Deadline deadline = new Deadline("return book", "2024-02-25 13:00");
        Event event = new Event("project meeting", "2024-02-25 13:00", "2024-02-25 15:00");
        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);
        list.add(todo);
        list.add(deadline);
        list.add(event);

        // Test if the size of the list is correct
        assertEquals(list.size(), taskList.size());

    }

    @Test
    public void testAdd() throws DateException {
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList();
        Todo todo = new Todo("read book");
        Deadline deadline = new Deadline("return book", "2024-02-25 13:00");
        Event event = new Event("project meeting", "2024-02-25 13:00", "2024-02-25 15:00");
        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);
        list.add(todo);
        list.add(deadline);
        list.add(event);

        // Test if the tasks are added to the list
        assertEquals(3, taskList.size());
        assertEquals(todo, taskList.get(0));
        assertEquals(deadline, taskList.get(1));
        assertEquals(event, taskList.get(2));
        assertEquals(list.get(0), taskList.get(0));
        assertEquals(list.get(1), taskList.get(1));
        assertEquals(list.get(2), taskList.get(2));

    }

    @Test
    public void testRemove() throws DateException {
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList();
        Todo todo = new Todo("read book");
        Deadline deadline = new Deadline("return book", "2024-02-25 13:00");
        Event event = new Event("project meeting", "2024-02-25 13:00", "2024-02-25 15:00");
        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);
        list.add(todo);
        list.add(deadline);
        list.add(event);

        // remove deadline
        taskList.remove(1);
        list.remove(1);

        // Test if the task is removed from the list
        assertEquals(2, taskList.size());
        assertEquals(todo, taskList.get(0));
        assertEquals(event, taskList.get(1));
        assertEquals(list, taskList.getList());

    }

    @Test
    public void testMarkTask() throws DateException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("read book");
        Deadline deadline = new Deadline("return book", "2024-02-25 13:00");
        Event event = new Event("project meeting", "2024-02-25 13:00", "2024-02-25 15:00");
        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);

        // mark deadline as done
        taskList.markTask(1);
        deadline.markAsDone();

        // Test if the task is marked as done
        assertEquals(deadline.getIsDone(), taskList.get(1).getIsDone());

    }

    @Test
    public void testUnmarkTask() throws DateException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("read book");
        Deadline deadline = new Deadline("return book", "2024-02-25 13:00");
        Event event = new Event("project meeting", "2024-02-25 13:00", "2024-02-25 15:00");
        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);

        // mark deadline as done
        taskList.markTask(1);
        deadline.markAsDone();

        // unmark deadline as undone
        taskList.unmarkTask(1);
        deadline.markAsUndone();

        // Test if the task is marked as undone
        assertEquals(deadline.getIsDone(), taskList.get(1).getIsDone());

    }

    @Test
public void testGetList() throws DateException {
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList();
        Todo todo = new Todo("read book");
        Deadline deadline = new Deadline("return book", "2024-02-25 13:00");
        Event event = new Event("project meeting", "2024-02-25 13:00", "2024-02-25 15:00");
        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);
        list.add(todo);
        list.add(deadline);
        list.add(event);

        // Test if the list is returned correctly
        assertEquals(list, taskList.getList());

    }

    @Test
    public void testFind() throws DateException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("read book");
        Deadline deadline = new Deadline("return book", "2024-02-25 13:00");
        Event event = new Event("project meeting", "2024-02-25 13:00", "2024-02-25 15:00");
        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);

        // find tasks with keyword "book"
        TaskList foundList = taskList.find("book");

        // Test if the tasks with keyword "book" are found
        assertEquals(2, foundList.size());
        assertEquals(todo, foundList.get(0));
        assertEquals(deadline, foundList.get(1));

    }

}

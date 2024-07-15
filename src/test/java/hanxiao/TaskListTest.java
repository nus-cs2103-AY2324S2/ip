package hanxiao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import hanxiao.exception.DuplicateTaskException;
import org.junit.jupiter.api.Test;

import hanxiao.task.Deadline;
import hanxiao.task.Task;
import hanxiao.task.Todo;

public class TaskListTest {

    @Test
    void test_getLength() {
        ArrayList<Task> input = new ArrayList<>();
        input.add(new Todo("todo task"));
        input.add(new Deadline("deadline task", LocalDate.parse("2002-11-21")));
        TaskList tl = new TaskList(input);

        assertEquals(2, tl.getListLength());

        input.remove(0);
        tl = new TaskList(input);
        assertEquals(1, tl.getListLength());
    }

    @Test
    void test_removeTask() {
        ArrayList<Task> input = new ArrayList<>();
        Task taskOne = new Deadline("deadline task", LocalDate.parse("2002-11-21"));
        Task taskTwo = new Todo("todo task");
        input.add(taskOne);
        input.add(taskTwo);
        TaskList tl = new TaskList(input);

        assertEquals(taskOne, tl.removeTask(0));

        assertEquals(taskTwo, tl.removeTask(0));
    }

    @Test
    void test_getTask() {
        ArrayList<Task> input = new ArrayList<>();
        Task taskOne = new Deadline("deadline task", LocalDate.parse("2002-11-21"));
        Task taskTwo = new Todo("todo task");
        input.add(taskOne);
        input.add(taskTwo);
        TaskList tl = new TaskList(input);

        assertEquals(input.get(0), tl.getTask(0));

        assertEquals(input.get(1), tl.getTask(1));
    }

    @Test
    void test_getTaskList() {
        ArrayList<Task> input = new ArrayList<>();
        Task taskOne = new Deadline("deadline task", LocalDate.parse("2002-11-21"));
        Task taskTwo = new Todo("todo task");
        input.add(taskOne);
        input.add(taskTwo);
        TaskList tl = new TaskList(input);

        assertEquals(input, tl.getTaskList());
    }

    @Test
    void test_addTask_throwHanxiaoException() throws DuplicateTaskException {
        TaskList tl = new TaskList(new ArrayList<>());
        tl.addTask(new Deadline("deadline task", LocalDate.parse("2002-11-21")));

        assertEquals(1, tl.getListLength());
    }
}

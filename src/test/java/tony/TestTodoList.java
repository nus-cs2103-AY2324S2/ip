package tony;

import tony.tasks.Task;
import tony.tasks.Todo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTodoList {
    private TodoList todoList;

    @BeforeEach
    public void setUp() {
        // Initialize a new TodoList before each test
        todoList = new TodoList();
    }

    @Test
    public void testAddTask() {
        Task task = new Todo("Example Task");
        todoList.add(task);
        assertEquals(1, todoList.size());
    }

    @Test
    public void testMarkTaskAsDone() {
        Task task = new Todo("Example Task");
        todoList.add(task);
        todoList.mark("1");
        assertTrue(task.isDone());
    }

    @Test
    public void testUnmarkTaskAsDone() {
        Task task = new Todo("Example Task");
        todoList.add(task);
        todoList.mark("1");
        todoList.unmark("1");
        assertFalse(task.isDone());
    }

    @Test
    public void testDeleteTask() {
        Task task = new Todo("Example Task");
        todoList.add(task);
        todoList.delete("1");
        assertEquals(0, todoList.size());
    }

}

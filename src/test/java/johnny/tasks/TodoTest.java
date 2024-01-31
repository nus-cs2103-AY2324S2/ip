package johnny.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void toString_unMarkedTodo_success() {
        ToDo todo = new ToDo("sleep");
        assertEquals("[T][ ] sleep", todo.toString());
    }

    @Test
    public void toString_markedTodo_success() {
        ToDo todo = new ToDo("sleep");
        todo.mark();
        assertEquals("[T][X] sleep", todo.toString());
    }

    @Test
    public void addToFile_unMarkedTodo_success() {
        ToDo todo = new ToDo("sleep");
        assertEquals("T | 0 | sleep\n", todo.addToFile());
    }

    @Test
    public void addToFile_markedTodo_success() {
        ToDo todo = new ToDo("sleep");
        todo.mark();
        assertEquals("T | 1 | sleep\n", todo.addToFile());
    }

}

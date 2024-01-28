package dino.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals(" T | 0 | Buy groceries", todo.toString());
    }
}

package riz.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toDoTest() {
        ToDo todo = new ToDo("shower");
        assertEquals("T |   | shower", todo.toString());
    }
}
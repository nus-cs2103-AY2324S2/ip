package riri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoCreationTest1() {
        Todo task = new Todo("return book");
        assertEquals(task.toString(), "[T][ ] return book");
    }
}

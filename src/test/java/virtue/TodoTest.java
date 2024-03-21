package virtue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoToStringTest() {
        Todo todo = new Todo("Finish this.");
        assertEquals(todo.toString(), "[T][ ] Finish this.");
        todo.markAsDone();
        assertEquals(todo.toString(), "[T][X] Finish this.");
    }

    @Test
    public void fileFormatTest() {
        Todo todo = new Todo("Do your job.");
        assertEquals(todo.fileFormat(), "T | 0 | Do your job.");
        todo.markAsDone();
        assertEquals(todo.fileFormat(), "T | 1 | Do your job.");
    }
}

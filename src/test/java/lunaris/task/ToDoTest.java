package lunaris.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toDoTestFile() {
        assertEquals(new ToDo("1", "return book").toFile(), "T | 1 |  return book");
    }

    @Test
    public void toDoTestString() {
        assertEquals(new ToDo("return book").toString(), "[T][ ]  return book");
    }
}

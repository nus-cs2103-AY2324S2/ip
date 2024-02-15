package chimp.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoToStringTest() {
        String expected = "[T] [ ] test";
        String result = new Todo("test", TaskStatus.UNMARKED).toString();
        assertEquals(expected, result);
    }
}

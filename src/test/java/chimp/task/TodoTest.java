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

    @Test
    public void todoMarkedToStringTest() {
        String expected = "[T] [X] Finish assignment";
        String result = new Todo("Finish assignment", TaskStatus.MARKED).toString();
        assertEquals(expected, result);
    }

    @Test
    public void todoWithLongDescriptionToStringTest() {
        String expected = "[T] [ ] Read and summarize research papers on AI";
        String result = new Todo("Read and summarize research papers on AI", TaskStatus.UNMARKED).toString();
        assertEquals(expected, result);
    }
}

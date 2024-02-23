package mychats.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void todoTest() {
        Todo output = new Todo("study");
        assertEquals("[T][ ] study", output.toString(), "toString() passed");
        assertEquals("T | 0 | study", output.toFileString(), "toFileString() passed");
        output.markTask();
        assertEquals("[T][X] study", output.toString(), "markTask() passed");
        assertEquals("T | 1 | study", output.toFileString(), "markTask() passed");
        output.unmarkTask();
        assertEquals("[T][ ] study", output.toString(), "unmarkTask() passed");
        assertEquals("T | 0 | study", output.toFileString(), "unmarkTask() passed");
    }

}


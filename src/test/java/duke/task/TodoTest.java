package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void todoTest() {
        Todo output = new Todo("study");
        assertEquals("[T][ ] study", output.toString(), "toString() passed");
        assertEquals("T | 0 | study", output.toFileString(), "toFileString() passed");
        output.markAsDone();
        assertEquals("[T][X] study", output.toString(), "markAsDone() passed");
        assertEquals("T | 1 | study", output.toFileString(), "markAsDone() passed");
        output.unMarkAsDone();
        assertEquals("[T][ ] study", output.toString(), "unMarkAsDone() passed");
        assertEquals("T | 0 | study", output.toFileString(), "unMarkAsDone() passed");
    }

}


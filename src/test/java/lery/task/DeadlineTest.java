package lery.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testGetType() {
        assertEquals("D", new Deadline("return book", "2024/01/22", false).getType());
    }

    @Test
    public void testGetExtraInfo() {
        assertEquals("(by: Jan 22 2024)", new Deadline("return book", "2024/01/22", false).getExtraInfo());
    }
    @Test
    public void testGetExtraInfoShortened() {
        assertEquals("2024-01-22", new Deadline("return book", "2024/01/22", false).getExtraInfoShortened());
    }
}

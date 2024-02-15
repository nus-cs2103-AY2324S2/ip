package luke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void testEventDate() {
        Deadline testDeadline = new Deadline("return book", "2/12/2019 1800");
        assertEquals(testDeadline.toString(), "[D][ ] return book (by: 12 Feb 2019 06:00pm)");
    }
}
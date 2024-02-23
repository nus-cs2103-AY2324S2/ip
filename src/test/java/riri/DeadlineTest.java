package riri;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void deadlineCreationTest1() {
        Deadline d = new Deadline("return book", "2/12/2019 1800");
        assertEquals("[D][ ] return book (by: Feb 12 2019)", d.toString());
    }
    @Test
    public void deadlineCreationTest2() {
        Deadline d = new Deadline("return book", "2/12/2019");
        assertEquals("[D][ ] return book (by: Feb 12 2019)", d.toString());
    }
    @Test
    public void deadlineCreationTest3() {
        Deadline d = new Deadline("return book", "2024-02-02");
        assertEquals("[D][ ] return book (by: Feb 2 2024)", d.toString());
    }
    @Test
    public void deadlineCreationTest4() {
        Deadline d = new Deadline("return book", "2024-02-02 1001");
        assertEquals("[D][ ] return book (by: Feb 2 2024)", d.toString());
    }
    @Test
    public void deadlineCreationTest5() {
        Deadline d = new Deadline("return book", "Feb 2 2024");
        assertEquals("[D][ ] return book (by: Feb 2 2024)", d.toString());
    }
}

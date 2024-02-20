package Shirmin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void stringRepresentationTest(){
        Deadline testDeadline = new Deadline("test", "2019-10-15 2300" );
        assertEquals("[D][ ] test (by: Oct 15 2019 23:00)", testDeadline.toString());
    }

}

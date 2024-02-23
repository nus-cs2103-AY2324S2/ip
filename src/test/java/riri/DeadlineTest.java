package riri;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineCreationTest1(){
        Deadline d = new Deadline("return book", "2/12/2019 1800");
        assertEquals("[D][ ] return book (by: Feb 12 2019)", d.toString());
    }
}
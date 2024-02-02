package task;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void testToString() {
        LocalDate date = LocalDate.of(2022 , 2 , 2);
        LocalTime time = LocalTime.of(10, 00);

        Deadline DeadlineWithCorrectInput = new Deadline("test deadline description", date, time);
        String tString1 = DeadlineWithCorrectInput.toString();
        assertEquals("[D][ ] test deadline description (by: 2 Feb 2022 10:00)", tString1);
    }
}

package TheAdvisor;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventsTest {
    @Test
    public void test1() throws TheAdvisorException {
        String input1 = "Sleep /from 2024-02-01 0000 /to 2024-02-01 0900";
        String[] eventArr1 = input1.split(" /from ");
        String[] timings1 = eventArr1[1].split(" /to");
        String startStr1 = timings1[0].trim();
        String endStr1 = timings1[1].trim();
        LocalDateTime start1 = LocalDateTime.parse(startStr1, Task.inputFormat);
        LocalDateTime end1 = LocalDateTime.parse(endStr1, Task.inputFormat);
        Events events1 = new Events(eventArr1[0], start1, end1);

        assertEquals("Sleep", events1.description);
        assertEquals(LocalDateTime.of(2024, 2, 1, 0, 0), events1.start);
        assertEquals(LocalDateTime.of(2024, 2, 1, 9, 0), events1.end);

        String input2 = "Play /from 2024-02-02 1000 /to 2024-02-02 2000";
        String[] eventArr2 = input2.split(" /from ");
        String[] timings2 = eventArr2[1].split(" /to");
        String startStr2 = timings2[0].trim();
        String endStr2 = timings2[1].trim();
        LocalDateTime start2 = LocalDateTime.parse(startStr2, Task.inputFormat);
        LocalDateTime end2 = LocalDateTime.parse(endStr2, Task.inputFormat);
        Events events2 = new Events(eventArr2[0], start2, end2);

        assertEquals("Play", events2.description);
        assertEquals(LocalDateTime.of(2024, 2, 2, 10, 0), events2.start);
        assertEquals(LocalDateTime.of(2024, 2, 2, 20, 0), events2.end);
    }

    @Test
    public void test2() throws TheAdvisorException {
        String input1 = "Sleep /from 2024/02/01 0000 /to 2024-02-01 0900";
        String[] eventArr1 = input1.split(" /from ");
        String[] timings1 = eventArr1[1].split(" /to");
        String startStr1 = timings1[0].trim();
        String endStr1 = timings1[1].trim();
        assertThrows(DateTimeException.class, () -> new Events(eventArr1[0],
                LocalDateTime.parse(startStr1, Task.inputFormat), LocalDateTime.parse(endStr1, Task.inputFormat)));

        String input2 = "Snore /from 2024-02-01 0000 /to 2024/02-01 0900";
        String[] eventArr2 = input2.split(" /from ");
        String[] timings2 = eventArr2[1].split(" /to");
        String startStr2 = timings2[0].trim();
        String endStr2 = timings2[1].trim();
        assertThrows(DateTimeException.class, () -> new Events(eventArr2[0],
                LocalDateTime.parse(startStr2, Task.inputFormat), LocalDateTime.parse(endStr2, Task.inputFormat)));

        String input3 = "Laugh /from 2024-02-01 0000 /to 2024-02-01";
        String[] eventArr3 = input3.split(" /from ");
        String[] timings3 = eventArr3[1].split(" /to");
        String startStr3 = timings3[0].trim();
        String endStr3 = timings3[1].trim();
        assertThrows(DateTimeException.class, () -> new Events(eventArr3[0],
                LocalDateTime.parse(startStr3, Task.inputFormat), LocalDateTime.parse(endStr3, Task.inputFormat)));
    }
}

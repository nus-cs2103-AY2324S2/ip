package theadvisor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void test1() throws TheAdvisorException {
        String input = "Sleep /by 2024-02-01 0000";
        String[] arrTask = input.split(" /by ");
        Deadline deadline = new Deadline(arrTask[0], LocalDateTime.parse(arrTask[1], Task.INPUT_FORMAT));

        assertEquals("Sleep", deadline.description);
        assertEquals(LocalDateTime.of(2024, 2, 1, 0, 0), deadline.by);

        String input2 = "return my book /by 2024-02-10 1200";
        String[] arrTask2 = input2.split(" /by ");
        Deadline deadline2 = new Deadline(arrTask2[0], LocalDateTime.parse(arrTask2[1], Task.INPUT_FORMAT));

        assertEquals("return my book", deadline2.description);
        assertEquals(LocalDateTime.of(2024, 2, 10, 12, 0), deadline2.by);
    }

    @Test
    public void test2() throws TheAdvisorException {
        String input1 = "Sleep /by 2024/02/01 0000";
        String[] arrTask1 = input1.split(" /by ");
        assertThrows(DateTimeException.class, () -> new Deadline(arrTask1[0],
                LocalDateTime.parse(arrTask1[1], Task.INPUT_FORMAT)));

        String input2 = "Sleep /by 2024-02-01";
        String[] arrTask2 = input2.split(" /by ");
        assertThrows(DateTimeException.class, () -> new Deadline(arrTask2[0],
                LocalDateTime.parse(arrTask2[1], Task.INPUT_FORMAT)));

        String input3 = "Sleep /by 2024/02-01 1800";
        String[] arrTask3 = input3.split(" /by ");
        assertThrows(DateTimeException.class, () -> new Deadline(arrTask3[0],
                LocalDateTime.parse(arrTask3[1], Task.INPUT_FORMAT)));

        String input4 = "Sleep /by 2023-02-01 180";
        String[] arrTask4 = input4.split(" /by ");
        assertThrows(DateTimeException.class, () -> new Deadline(arrTask3[0],
                LocalDateTime.parse(arrTask4[1], Task.INPUT_FORMAT)));
    }
}

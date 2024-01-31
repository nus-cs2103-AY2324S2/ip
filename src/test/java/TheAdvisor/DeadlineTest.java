package TheAdvisor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    public void test1() throws TheAdvisorException {
        String input = "Sleep /by 2024-02-01 0000";
        String[] arrTask = input.split(" /by ");
        Deadline deadline = new Deadline(arrTask[0], LocalDateTime.parse(arrTask[1], Task.inputFormat));

        assertEquals("Sleep", deadline.description);
        assertEquals(LocalDateTime.of(2024, 2, 1, 0, 0), deadline.by);
    }

    @Test
    public void test2() throws TheAdvisorException {
        String input1 = "Sleep /by 2024/02/01 0000";
        String[] arrTask1 = input1.split(" /by ");
        assertThrows(DateTimeException.class, () -> new Deadline(arrTask1[0], LocalDateTime.parse(arrTask1[1], Task.inputFormat)));

        String input2 = "Sleep /by 2024-02-01";
        String[] arrTask2 = input1.split(" /by ");
        assertThrows(DateTimeException.class, () -> new Deadline(arrTask2[0], LocalDateTime.parse(arrTask2[1], Task.inputFormat)));
    }
}

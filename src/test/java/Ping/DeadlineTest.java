package ping;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ping.exceptions.PingException;
import ping.job.Deadline;
import ping.timematch.DateTimeCheck; 

public class DeadlineTest {
    @Test
    public void todoCommand_ShouldHaveCorrectFormat() throws PingException {
        try {
            LocalDate dt = DateTimeCheck.timeCheckOnDate("09/04/2023");
            Deadline dl = new Deadline("Eat Apple ", dt);
            assertEquals("Eat Apple ", dl.getDescription());
            assertEquals(LocalDate.of(2023,4,9), dl.getBy());
        } catch (PingException e) {
            assertEquals("Invalid date format. Check whether it is format /by dd/MM/yyyy", e.getMessage());
        }
    }

    @Test
    public void String_ShouldBeCorrect() throws PingException {
        try {
            LocalDate dt = DateTimeCheck.timeCheckOnDate("09/04/2023");
            Deadline dl = new Deadline("Eat Apple", dt);
            String correct = "[D][ ] Eat Apple(by: 2023-04-09)";
            assertEquals(correct, dl.toString());
        } catch (PingException e) {
            assertEquals("Invalid date format. Check whether it is format /by dd/MM/yyyy", e.getMessage());
        }
    }
}

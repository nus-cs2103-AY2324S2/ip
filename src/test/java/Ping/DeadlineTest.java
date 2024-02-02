package Ping;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void todoCommand_ShouldHaveCorrectForamt() {
        LocalDate dt = DateTimeCheck.timeCheckOnDate("09/04/2023");
        Deadline dl = new Deadline("Eat Apple ", dt);
        assertEquals("Eat Apple ", dl.description);
        assertEquals(LocalDate.of(2023,4,9), dl.by);
    }

    @Test
    public void String_ShouldBeCorrect() {
        LocalDate dt = DateTimeCheck.timeCheckOnDate("09/04/2023");
        Deadline dl = new Deadline("Eat Apple", dt);
        String correct = "[D][ ] Eat Apple(by: 2023-04-09)";
        assertEquals(correct, dl.toString());

    }
}

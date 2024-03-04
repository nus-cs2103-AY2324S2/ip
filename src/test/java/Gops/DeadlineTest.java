package Gops;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void deadlineTest() {
        Deadline deadline = new Deadline("Graduate", "2026-05-05");
        String actualOutput = deadline.stringPrinter();
        String expectedOutput = "D | 0 | Graduate | by: May 05 2026";
        assertEquals(actualOutput, expectedOutput);
    }
}

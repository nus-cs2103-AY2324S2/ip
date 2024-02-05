package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {
    @Test
    public void DeadlinesToString() {
        Deadlines d = new Deadlines("deadline finish assignment", "2/5/2022 1530");
        assertEquals("[D][ ] deadline finish assignment (by: 2/5/2022 1530)", d.toString());
    }
}

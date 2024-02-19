package unim.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void DeadlinesToString() {
        Deadline d = new Deadline("deadline finish assignment", "2/5/2022 1530");
        assertEquals("[D][ ] deadline finish assignment (by: 2/5/2022 1530)", d.toString());
    }
}

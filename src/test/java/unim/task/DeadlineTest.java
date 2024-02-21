package unim.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlinesToString() {
        Deadline d = new Deadline("deadline finish assignment", "02/05/2022 1530");
        assertEquals("[ ] [D] deadline finish assignment (by: 02/05/2022 1530)", d.toString());
    }
}

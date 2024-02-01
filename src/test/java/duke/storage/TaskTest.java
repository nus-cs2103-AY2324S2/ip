package duke.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void getStatusIcon_test() {
        String sampleInput = "todo gym";
        Task sample = new Task(sampleInput, "gym");
        assertEquals("todo gym", sample.getOriginalCommand());
        assertEquals(" ", sample.getStatusIcon());
        sample.mark();
        assertEquals("X", sample.getStatusIcon());
    }

}

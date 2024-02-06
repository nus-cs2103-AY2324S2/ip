package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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

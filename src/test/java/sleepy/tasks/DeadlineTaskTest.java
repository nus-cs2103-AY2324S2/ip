package sleepy.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    @Test
    public void getDescription_normalDeadline_success() {
        DeadlineTask testDeadlineTask = new DeadlineTask("complete quiz", "this Friday");
        assertEquals("[D][ ] complete quiz (by: this Friday)", testDeadlineTask.getDescription());
    }
    @Test
    public void getDescription_dateChange_success() {
        DeadlineTask testDeadlineTask = new DeadlineTask("submit ip", "2024-02-23");
        assertEquals("[D][ ] submit ip (by: 23 Feb 2024)", testDeadlineTask.getDescription());
    }
}

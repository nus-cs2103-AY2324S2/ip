package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toDbString_exampleDeadline_success() {
        assertEquals("D|0|Do Math Homework|2023-12-12", new Deadline("Do Math Homework", "2023-12-12").toDbString());
    }

}

package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * Encapsulate the test for Deadline class.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class DeadlineTest {
    @Test
    public void Deadline_toString() {
        assertEquals("[D][ ] assignment (by: Jan-28-2024)",
                new Deadline("assignment", LocalDate.of(2024, 1, 28)).toString());
    }

    @Test
    public void Deadline_toStorageFormat() {
        assertEquals("D |   | assignment | 28-1-24",
                new Deadline("assignment", LocalDate.of(2024, 1, 28)).convertToStorageFormat());
    }
}

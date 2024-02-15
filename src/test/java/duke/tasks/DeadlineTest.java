package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Encapsulate the test for Deadline class.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class DeadlineTest {
    @Test
    public void Deadline_toString() {
        Collection<String> collection = Arrays.asList("fun", "engaging", "school");
        assertEquals("[D][ ] assignment (by: Jan-28-2024) Tags:[fun, engaging, school]",
                new Deadline("assignment", LocalDate.of(2024, 1, 28), new ArrayList<>(collection)).toString());
    }

    @Test
    public void Deadline_toStorageFormat() {
        Collection<String> collection = Arrays.asList("fun", "engaging", "school");
        assertEquals("D |   | assignment | 28-1-24 | fun engaging school",
                new Deadline("assignment", LocalDate.of(2024, 1, 28), new ArrayList<>(collection))
                        .convertToStorageFormat());
    }
}

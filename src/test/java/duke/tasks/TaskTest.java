package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;

/**
 * Encapsulate the test for Task class.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class TaskTest {
    private static final Collection<String> collection = Arrays.asList("fun", "engaging", "school");

    @Test
    public void Task_toString() {
        assertEquals("[T][X] homework", new Task("homework", "T", true, new ArrayList<>(collection)).toString());
    }

    @Test
    public void Task_toStorageFormat() {
        assertEquals("T | X | homework",
                new Task("homework", "T", true, new ArrayList<>(collection)).convertToStorageFormat());
    }
}

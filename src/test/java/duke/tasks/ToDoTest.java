package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;

/**
 * Encapsulate the test for ToDo class.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class ToDoTest {
    private static final Collection<String> collection = Arrays.asList("fun", "engaging", "school");

    @Test
    public void ToDo_toString() {
        assertEquals("[T][X] homework Tags:[fun, engaging, school]", new ToDo("homework", true, new ArrayList<>(collection)).toString());
    }

    @Test
    public void ToDo_toStorageFormat() {
        assertEquals("T | X | homework | fun engaging school",
                new ToDo("homework", true, new ArrayList<>(collection)).convertToStorageFormat());
    }
}

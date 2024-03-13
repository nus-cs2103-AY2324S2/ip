/**
 * JUnit test class for the {@link duke.tasks.ToDo} class.
 */
package duke.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.ToDo;

public class TodoTest {
    /**
     * JUnit test method for the {@link duke.tasks.ToDo#toString()} method.
     * Tests the string representation of a ToDo task.
     */
    @Test
    public void todoToStringTest() {
        // Assertion to check if the string representation matches the expected output
        assertEquals("[T][ ] homework", new ToDo("homework").toString());
    }
}

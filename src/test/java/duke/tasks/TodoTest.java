package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.tasks.EmptyDescriptionException;

public class TodoTest {
    @Test
    public void constructor_emptyDescription_emptyDescriptionExceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new Todo(""));
    }
}

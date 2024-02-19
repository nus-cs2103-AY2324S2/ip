package arc.tasks;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import arc.exceptions.tasks.EmptyDescriptionException;

public class TodoTest {
    @Test
    public void constructor_emptyDescription_emptyDescriptionExceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new Todo(""));
    }
}

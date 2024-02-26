package destiny;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void getString_validInput_properString() throws DestinyException {
        assertEquals("[T][ ] test",
                new ToDo("test").toString());
    }

    @Test
    public void getString_invalidInput_exceptionThrown() {
        try {
            assertEquals("[T][ ]",
                    new ToDo("").toString());
        } catch (DestinyException e) {
            assertEquals("Something went wrong...\n"
                    + "Please enter a description for this todo task", e.getMessage());
        }

    }
}

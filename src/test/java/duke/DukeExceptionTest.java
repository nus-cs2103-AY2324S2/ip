package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeExceptionTest {

    @Test
    void dukeExceptionTest() {
        String errorMessage = "Test error message";

        DukeException dukeException = new DukeException(errorMessage);

        assertEquals(
                "____________________________________________________________\n" +
                        "   OOPS!!! " + errorMessage + "\n" +
                        "____________________________________________________________\n",
                dukeException.getMessage()
        );
    }
}

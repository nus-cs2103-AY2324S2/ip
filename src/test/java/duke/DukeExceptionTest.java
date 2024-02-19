package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import iggly.duke.DukeException;
class DukeExceptionTest {

    @Test
    void dukeExceptionTest() {
        String errorMessage = "Test error message";

        DukeException dukeException = new DukeException(errorMessage);

        assertEquals("OOPS!!! " + errorMessage + " \uD83D\uDC27\n", dukeException.getMessage());
    }
}

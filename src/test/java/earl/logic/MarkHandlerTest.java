package earl.logic;

import earl.logic.stubs.TaskListStub;
import earl.logic.stubs.UiStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MarkHandlerTest {

    private static final PrintStream originalOut = System.out;

    private static final ByteArrayOutputStream testingOut =
            new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(testingOut));
    }

    @Test
    void handle_normalCommand_success() throws Exception {
        String[] command = {"mark", "1"};
        Handler handler = new MarkHandler(command);
        handler.handle(new TaskListStub(), new UiStub());
        assertEquals("Item marked as done.\r\n", testingOut.toString());
    }

    @Test
    void handle_nonIntegerInput_exceptionThrown() {
        try {
            String[] command = {"a"};
            Handler handler = new MarkHandler(command);
            handler.handle(new TaskListStub(), new UiStub());
            fail();
        } catch (Exception e) {
            assertEquals("Error, not a valid item number within range.\n"
                    + "\tExample use:\n\tmark 3", e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
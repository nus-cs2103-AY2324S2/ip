package earl.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import earl.logic.stubs.TaskListStub;
import earl.logic.stubs.UiStub;

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
        Handler handler = HandlerType.mark.createHandler("1");
        handler.handle(new TaskListStub(), new UiStub());
        String newLine = System.lineSeparator();
        assertEquals("Item(s) marked as done." + newLine
                + "1.[ ] TaskStub" + newLine,
                testingOut.toString());
    }

    @Test
    void handle_nonIntegerInput_exceptionThrown() {
        try {
            Handler handler = HandlerType.mark.createHandler("a");
            handler.handle(new TaskListStub(), new UiStub());
            fail();
        } catch (Exception e) {
            assertEquals("Error, indices format invalid."
                    + " Example format: 1 4-7 9-10", e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}

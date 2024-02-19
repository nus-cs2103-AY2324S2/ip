package earl.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import earl.exceptions.EarlException;
import earl.util.stubs.TaskListStub;
import earl.util.stubs.UiStub;

class TodoHandlerTest {

    private static final PrintStream originalOut = System.out;
    private static final ByteArrayOutputStream testingOut =
            new ByteArrayOutputStream();
    private static final String NEWLINE = System.lineSeparator();
    private static final String PADDING = " ".repeat(4);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(testingOut));
    }

    @Test
    void handle_normalCommand_success() throws Exception {
        Handler handler = HandlerType.TODO.createHandler("test");
        handler.handle(new TaskListStub(), new UiStub());
        String expected = "A new TODO [T][ ] test"
                + NEWLINE
                + "hath been appended to the roster of responsibilities."
                + NEWLINE
                + "The ledger of tasks bears witness to 10 endeavour(s)."
                + NEWLINE;
        assertEquals(expected, testingOut.toString());
    }

    @Test
    void handle_emptyDescription_exceptionThrown() {
        try {
            Handler handler = HandlerType.TODO.createHandler("");
            handler.handle(new TaskListStub(), new UiStub());
            fail();
        } catch (EarlException e) {
            String expected = "The description is devoid of detail." + NEWLINE
                    + PADDING + "Example use:" + NEWLINE
                    + PADDING + "todo <task name>";
            assertEquals(expected, e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        testingOut.reset();
        System.setOut(originalOut);
    }
}

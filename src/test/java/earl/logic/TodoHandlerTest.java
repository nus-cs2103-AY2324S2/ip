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

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(testingOut));
    }

    @Test
    void handle_normalCommand_success() throws Exception {
        Handler handler = HandlerType.todo.createHandler("test");
        handler.handle(new TaskListStub(), new UiStub());
        String newLine = System.lineSeparator();
        String padding = " ".repeat(4);
        String expected = "A new todo, by virtue of your decree," + newLine
                + "hath been appended to the roster of responsibilities."
                + newLine
                + padding + "TaskStub, " + newLine
                + "The ledger of tasks bears witness to 10 endeavour(s)."
                + newLine;
        assertEquals(expected, testingOut.toString());
    }

    @Test
    void handle_emptyDescription_exceptionThrown() {
        try {
            Handler handler = HandlerType.todo.createHandler("");
            handler.handle(new TaskListStub(), new UiStub());
            fail();
        } catch (EarlException e) {
            assertEquals("The description is devoid of detail.",
                    e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}

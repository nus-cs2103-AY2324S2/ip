package earl.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import earl.util.stubs.TaskListStub;
import earl.util.stubs.UiStub;

class FindHandlerTest {

    private static final PrintStream originalOut = System.out;
    private static final ByteArrayOutputStream testingOut =
            new ByteArrayOutputStream();
    private static final String NEWLINE = System.lineSeparator();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(testingOut));
    }

    @Test
    void handle_noMatches_printNoMatchMessage() throws Exception {
        Handler handler = HandlerType.FIND.createHandler("test");
        handler.handle(new TaskListStub(), new UiStub());
        assertEquals("No matches have been discerned." + NEWLINE,
                testingOut.toString());
    }

    @Test
    void handle_match_printMatches() throws Exception {
        Handler handler = HandlerType.FIND.createHandler("stub");
        handler.handle(new TaskListStub(), new UiStub());
        String expected = "1 task(s) of congruence have been uncovered."
                + NEWLINE
                + "1. [T][ ] stub" + NEWLINE;
        assertEquals(expected, testingOut.toString());
    }

    @AfterEach
    void tearDown() {
        testingOut.reset();
        System.setOut(originalOut);
    }
}

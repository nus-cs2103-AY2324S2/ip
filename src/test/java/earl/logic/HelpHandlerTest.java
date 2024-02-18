package earl.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import earl.util.stubs.TaskListStub;
import earl.util.stubs.UiStub;

class HelpHandlerTest {

    private static final PrintStream originalOut = System.out;
    private static final ByteArrayOutputStream testingOut =
            new ByteArrayOutputStream();
    private static final String NEWLINE = System.lineSeparator();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(testingOut));
    }

    @Test
    void handle_unknownInput_printsValidCommands() throws Exception {
        Handler handler = HandlerType.HELP.createHandler("test");
        handler.handle(new TaskListStub(), new UiStub());
        String validCommands = Stream.of(HandlerType.values())
                .map((x) -> "-" + x.toString().toLowerCase() + NEWLINE)
                .reduce("", (x, y) -> x + y);
        String expected = "Input defies parsing: test" + NEWLINE
                + "Pray, issue a command forthwith." + NEWLINE
                + "The sanctioned commands unfold:" + NEWLINE
                + validCommands;
        assertEquals(expected, testingOut.toString());
    }

    @AfterEach
    void tearDown() {
        testingOut.reset();
        System.setOut(originalOut);
    }
}

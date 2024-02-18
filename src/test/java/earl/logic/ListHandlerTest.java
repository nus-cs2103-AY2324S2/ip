package earl.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import earl.util.stubs.TaskListStub;
import earl.util.stubs.UiStub;

class ListHandlerTest {

    private static final PrintStream originalOut = System.out;
    private static final ByteArrayOutputStream testingOut =
            new ByteArrayOutputStream();
    private static final String NEWLINE = System.lineSeparator();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(testingOut));
    }

    @Test
    void handle_listEmpty_printsEmptyMessage() throws Exception {
        Handler handler = HandlerType.LIST.createHandler("");
        handler.handle(new TaskListStub(), new UiStub());
        assertEquals("A void of contents prevails." + NEWLINE,
                testingOut.toString());
    }

    @Test
    void handle_argsProvided_printsErrorMessage() throws Exception {
        Handler handler = HandlerType.LIST.createHandler("test");
        handler.handle(new TaskListStub(), new UiStub());
        assertEquals("No arguments should trail in the wake "
                + "of this command." + NEWLINE, testingOut.toString());
    }

    @AfterEach
    void tearDown() {
        testingOut.reset();
        System.setOut(originalOut);
    }
}

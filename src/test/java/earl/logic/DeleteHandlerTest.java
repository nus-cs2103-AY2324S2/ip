package earl.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import earl.util.stubs.TaskListStub;
import earl.util.stubs.UiStub;

class DeleteHandlerTest {

    private static final PrintStream originalOut = System.out;

    private static final ByteArrayOutputStream testingOut =
            new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(testingOut));
    }

    @Test
    void handle_normalInput_success() throws Exception {
        Handler handler = HandlerType.DELETE.createHandler("1");
        handler.handle(new TaskListStub(), new UiStub());
        String newLine = System.lineSeparator();
        assertEquals("Item(s) heretofore have been expunged." + newLine
                + "1.TaskStub, " + newLine, testingOut.toString());
    }

    @Test
    void handle_nonIntegerInput_exceptionThrown() {
        try {
            Handler handler = HandlerType.DELETE.createHandler("a");
            handler.handle(new TaskListStub(), new UiStub());
            fail();
        } catch (Exception e) {
            assertEquals("The indices' format is fraught with invalidity."
                    + " Example format: 1 4-7 9-10", e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}

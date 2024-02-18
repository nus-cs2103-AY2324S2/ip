package earl.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UiTest {

    private static final PrintStream originalOut = System.out;

    private static final ByteArrayOutputStream testingOut =
            new ByteArrayOutputStream();

    private static final String NEWLINE = System.lineSeparator();
    private static final String DIVIDER = "_".repeat(60);
    private static final String PADDING = " ".repeat(4);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(testingOut));
    }

    @Test
    void getResponse_singleLine_success() {
        Ui ui = new Ui();
        ui.makeResponse("A");
        assertEquals("A", ui.getResponse());
    }

    @Test
    void makeResponse_multipleLines_success() {
        Ui ui = new Ui();
        ui.makeResponse("A", "B");
        String expected = PADDING + DIVIDER + NEWLINE
                + PADDING + "A" + NEWLINE
                + PADDING + "B" + NEWLINE
                + PADDING + DIVIDER + NEWLINE;
        assertEquals(expected, testingOut.toString());
    }

    @Test
    void buildResponse_multipleLines_success() {
        Ui ui = new Ui();
        ui.buildResponse("A");
        ui.buildResponse("B");
        ui.completeResponse();
        String expected = PADDING + DIVIDER + NEWLINE
                + PADDING + "A" + NEWLINE
                + PADDING + "B" + NEWLINE
                + PADDING + DIVIDER + NEWLINE;
        assertEquals(expected, testingOut.toString());
    }

    @AfterEach
    void tearDown() {
        testingOut.reset();
        System.setOut(originalOut);
    }
}

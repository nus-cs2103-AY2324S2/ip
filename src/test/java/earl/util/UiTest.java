package earl.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    private static final PrintStream originalOut = System.out;

    private static final ByteArrayOutputStream testingOut =
            new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(testingOut));
    }

    @Test
    void makeResponse_multipleLines_success() {
        Ui ui = new Ui();
        ui.makeResponse("A", "B");
        String divider = "    " + "_".repeat(60) + "\r\n";
        String expected = divider + "    A\r\n    B\r\n" + divider;
        assertEquals(expected, testingOut.toString());
    }

    @Test
    void getResponse_singleLine_success() {
        Ui ui = new Ui();
        ui.makeResponse("A");
        String divider = "    " + "_".repeat(60) + "\n";
        String expected = divider + "    A\n" + divider;
        assertEquals(expected, ui.getResponse());
    }

    @AfterEach
    void tearDown() {
        testingOut.reset();
        System.setOut(originalOut);
    }
}
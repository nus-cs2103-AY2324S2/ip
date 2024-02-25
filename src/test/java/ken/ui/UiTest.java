package ken.ui;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void testByeMessage() {
        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.byeMessage();

        System.setOut(System.out);

        String expected = "Beach off!";
        String actual = outContent.toString().trim();  // Trim to remove leading/trailing whitespaces

        // Use assertEquals with trimmed strings
        assertEquals(expected, actual);
    }
}

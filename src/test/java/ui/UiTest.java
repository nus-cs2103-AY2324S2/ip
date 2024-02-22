package ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void testGreet() {
        Ui ui = new Ui();

        assertEquals("    Hi! I am your favourite friend, Lelu :)\n    What can I do for you?\n", ui.greet());
    }

    @Test
    public void testExit() {
        Ui ui = new Ui();
        assertEquals("    Ok bye, you shall be missed :(", ui.exit());
    }

    @Test
    public void testDateFormatInstructions() {
        Ui ui = new Ui();
        assertEquals("    Your date should be in this format:\n    <yyyy-MM-dd HH:mm> e.g. 2024-02-03 15:25\n\n",
                ui.dateFormatInstructions());
    }
}

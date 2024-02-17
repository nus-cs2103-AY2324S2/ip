package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class UiTest {
    Ui ui = new Ui();
    @Test
    public void testShortCuts() {
        assertEquals(ui.getCommand("deadline something /by 2202"), ui.getCommand("d something /by 2202"));
    }

}

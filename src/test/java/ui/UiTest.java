package ui;

import Fredricksen.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void UiOutput_correctFormat() {
        Ui ui = new Ui();
        assertEquals(ui.output("deadline homework /by 2/12/2019 1800"),
                "____________________________________________________________\n"
                        + "deadline homework /by 2/12/2019 1800\n"
                 + "____________________________________________________________\n");
    }

    @Test
    public void UiOutput2_correctFormat() {
        Ui ui = new Ui();
        assertEquals(ui.output("todo laundry"),
                "____________________________________________________________\n"
                        + "todo laundry\n"
                        + "____________________________________________________________\n");
    }
}

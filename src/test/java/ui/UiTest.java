package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fredricksen.ui.Ui;

public class UiTest {
    @Test
    public void uiOutput_correctFormat_success() {
        Ui ui = new Ui();
        assertEquals("deadline homework /by 2/12/2019 1800", ui.output("deadline homework /by 2/12/2019 1800"));
    }

    @Test
    public void uiOutput2_correctFormat_success() {
        Ui ui = new Ui();
        assertEquals("todo laundry", ui.output("todo laundry"));
    }
}

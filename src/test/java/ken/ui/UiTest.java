package ken.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void testByeMessage() {

        Ui ui = new Ui();

        String expected = "Beach off!\n";
        String actual = ui.getByeMessage().getMessage();

        assertEquals(expected, actual);
    }


    @Test
    public void testWelcomeMessage() {

        Ui ui = new Ui();

        String expected = "Hi Barbie!\nI'm Ken!\nWhat would you like to beach today?\np.s. say help if you need!\n";
        String actual = ui.getWelcomeMessage().getMessage();

        assertEquals(expected, actual);
    }

}

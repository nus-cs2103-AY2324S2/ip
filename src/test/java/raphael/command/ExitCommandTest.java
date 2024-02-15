package raphael.command;
import org.junit.jupiter.api.Test;
import raphael.ui.Ui;

public class ExitCommandTest {

    @Test
    public void exitCommandTest() {
        new ExitCommand().execute(null, new Ui(), null);
    }
}

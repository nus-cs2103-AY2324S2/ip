package raphael.command;
import org.junit.jupiter.api.Test;
public class ExitCommandTest {

    @Test
    public void exitCommandTest() {
        new ExitCommand().execute(null, null, null);
    }
}

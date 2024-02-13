package Duke;
import org.junit.jupiter.api.Test;

/**
 * JUnit test for Duke class.
 */
public class DukeTest {

    /**
     * Tests the constructor of Duke.
     */
    @Test
    public void dukeConstructorTest() {
        Duke duke = new Duke("./data/test.txt");
    }
}

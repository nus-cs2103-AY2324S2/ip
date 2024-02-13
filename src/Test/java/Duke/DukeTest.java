package Duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dukeConstructorTest() {
        Duke duke = new Duke("./data/test.txt");
    }
}

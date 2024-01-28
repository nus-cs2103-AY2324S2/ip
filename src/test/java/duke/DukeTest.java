package duke;

import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    @Test
    public void FileGenerationTest() {
        File file = new File("data/help.txt");
        file.delete();
        Duke duke = new Duke("data/help.txt");
        assertTrue(file.exists()); 
        file.delete();
    }
}

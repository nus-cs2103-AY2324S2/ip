package duke;

import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    @Test
    public void FileGenerationTest() {
        String filePath = "data/help.txt";
        File file = new File("data/help.txt");
        file.delete();
        Duke duke = new Duke();
        duke.loadSave(filePath);
        assertTrue(file.exists());
        file.delete();
    }
}

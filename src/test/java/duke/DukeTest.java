package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;


public class DukeTest {
    @Test
    public void fileGenerationTest() {
        String filePath = "data/help.txt";
        File file = new File("data/help.txt");
        file.delete();
        Duke duke = new Duke();
        duke.loadSave(filePath);
        assertTrue(file.exists());
        file.delete();
    }
}

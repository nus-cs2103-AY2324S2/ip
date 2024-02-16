package grizzly;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;


public class GrizzlyTest {
    @Test
    public void fileGenerationTest() {
        String filePath = "data/help.txt";
        File file = new File("data/help.txt");
        file.delete();
        Grizzly grizzly = new Grizzly();
        grizzly.loadSave(filePath);
        assertTrue(file.exists());
        file.delete();
    }
}

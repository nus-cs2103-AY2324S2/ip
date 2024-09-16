package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @BeforeEach
    void setUp() throws IOException {
        // Delete the directory and file before each test
        Files.deleteIfExists(Paths.get("./data/taskList.txt"));
        Files.deleteIfExists(Paths.get("./data"));
    }

    @Test
    void create_noExistingDirectoryAndFile_directoryAndFileCreated() throws IOException {
        Storage.init();

        File directory = new File("./data");
        assertTrue(directory.exists());

        File file = new File("./data/taskList.txt");
        assertTrue(file.exists());
    }

    @Test
    void create_existingDirectoryAndFile_directoryAndFileStillExist() throws IOException {
        // Create the directory and file before calling Storage.init
        new File("./data").mkdirs();
        new File("./data/taskList.txt").createNewFile();

        Storage.init();

        File directory = new File("./data");
        assertTrue(directory.exists());

        File file = new File("./data/taskList.txt");
        assertTrue(file.exists());
    }
}
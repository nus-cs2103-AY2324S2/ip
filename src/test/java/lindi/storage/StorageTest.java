package lindi.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import lindi.task.TaskList;

/**
 * Tester for Storage class. Uses a mock data folder
 */
public class StorageTest {
    private final String testDataDir = "./.testData";
    private final String testFile = ".testFile.txt";

    public StorageTest() {
        File testDataDirectory = new File(testDataDir);
        if (testDataDirectory.exists()) {
            throw new RuntimeException("Test cannot continue, folder exists already.\n"
                    + "There may be user generated files which will be overwritten if test executed");
        }
    }
    @Test
    public void saveToFile_emptyTaskList_fileCreatedAndEmpty() {
        Storage storage = new Storage(this.testDataDir, this.testFile);

        storage.saveToFile(new TaskList());

        File dir = new File(this.testDataDir);
        File f = new File(dir, this.testFile);

        try {
            Scanner fileScanner = new Scanner(f);
            assert !fileScanner.hasNextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            f.delete();
            dir.delete();
        }
    }
}

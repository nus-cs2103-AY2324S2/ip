package aegis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * StorageTest class contains methods for performing JUnit tests for WriteTaskListData() in TaskList class.
 */
public class StorageTest {
    /**
     * Tests WriteTaskListData() with valid input.
     */
    @Test
    public void WriteTaskListDataValidTest() {
        String filePath = "./src/test/data/storagetest_valid.txt";
        String directoryPath = "./src/test/data";
        Storage storage = new Storage(directoryPath, filePath);
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Dummy task 1"));
        taskList.addTask(new ToDo("Dummy task 2"));
        taskList.addTask(new ToDo("Dummy task 3"));

        try {
            storage.writeTaskListData(taskList);
        } catch (IOException e) {
            System.out.println("IO error detected");
        }
        String correctContents = "T|0|Dummy task 1"
                + "T|0|Dummy task 2"
                + "T|0|Dummy task 3";

        File fileToVerify = new File(filePath);
        String fileContents = "";
        try {
            Scanner sc = new Scanner(fileToVerify);
            while (sc.hasNextLine()) {
                fileContents += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file");
        }
        assertEquals(fileContents, correctContents);
    }
}
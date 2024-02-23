package yippee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import yippee.exceptions.YippeeException;
import yippee.exceptions.YippeeFileException;
import yippee.tasks.Deadline;
import yippee.tasks.Event;
import yippee.tasks.ToDo;

public class StorageTest {
    /*@Test
    public void storeDataTest() throws YippeeException, IOException {
        File file = new File("storageTest.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new YippeeFileException("Error creating the file : " + e.getMessage());
            }
        }

        Scanner fileSc = new Scanner(file);
        Storage testStorage = new Storage("storageTest.txt");
        TaskList testList = new TaskList();
        testList.addStoredTask(new ToDo("Task A"));
        testList.addStoredTask(new Deadline("Task B", "2022-09-28"));
        testList.addStoredTask(new Event("Task C", "2023-09-09", "2023-09-18"));
        testStorage.storeData(testList);

        String currentTask = fileSc.nextLine();
        assertEquals("T|false|Task A", currentTask);

        currentTask = fileSc.nextLine();
        assertEquals("D|false|Task B|2022-09-28", currentTask);

        currentTask = fileSc.nextLine();
        assertEquals("E|false|Task C|2023-09-09|2023-09-18", currentTask);
    }*/

    @Test
    public void testLoadSuccessful() throws YippeeException, IOException {
        File file = new File("storageTest.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new YippeeFileException("Error creating the file : " + e.getMessage());
            }
        }
        String testContent = "T|false|Task A\nD|true|Task B|2022-02-02";
        FileWriter writer = new FileWriter("storageTest.txt");
        writer.write(testContent);
        writer.close();

        Storage storage = new Storage("storageTest.txt");
        TaskList testTasks = storage.load();

        assertEquals(2, testTasks.getList().size());
    }

}

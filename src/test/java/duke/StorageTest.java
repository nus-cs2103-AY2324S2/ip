package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeFileException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void storeDataTest() throws DukeException, IOException {
            File file = new File("storageTest.txt");
            if (!file.exists()) {
                    try {
                            file.createNewFile();
                    } catch (IOException e) {
                            throw new DukeFileException("Error creating the file : " + e.getMessage());
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
    }

    @Test
    public void testLoadSuccessful() throws DukeException, IOException {
            File file = new File("storageTest.txt");
            if (!file.exists()) {
                    try {
                            file.createNewFile();
                    } catch (IOException e) {
                            throw new DukeFileException("Error creating the file : " + e.getMessage());
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

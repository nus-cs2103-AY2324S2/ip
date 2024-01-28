package duke.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

public class StorageTest {

    @Test
    public void testStorageFileCreation() {
        File file = new File("data/test.txt");
        file.delete();
        try {
            Storage s = new Storage("data/test.txt");
            assertTrue(file.exists()); 
            file.delete();
        } catch (IOException e) {
            fail();
            file.delete();
        }
    }

    @Test
    public void testStorageReading() {
        
        try {
            File file = new File("data/test.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write("[T]|[ ]|1\n" +
                    "[D]|[X]|task2|12/11/2001, 16:00\n" +
                    "[E]|[ ]|task 3|12/11/2001, 15:00|13/11/2001, 16:00");

            fw.close();
            Storage s = new Storage("data/test.txt");
            Ui ui = new Ui();
            TaskList taskList = s.readSaveData(ui);

            assertTrue(taskList.size() == 3);
            assertTrue(taskList.get(1) instanceof Todo);
            assertTrue(taskList.get(2) instanceof Deadline);
            assertTrue(taskList.get(3) instanceof Event);
            file.delete();
            
        } catch (IOException e) {
            fail();
        }
    }
}

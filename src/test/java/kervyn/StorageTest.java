package kervyn;
import kervyn.Tasks.Task;
import kervyn.Tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class StorageTest {
    ArrayList<Task> testArr = new Storage("testData/tasks.txt").readTasks();
    public StorageTest() throws IOException {
    }

    @Test
    public void writeToFile_withoutFileExisting() {
        short result = new Storage("testData/tasks.txt").writeToFile("[T] [ ] software engineering");
        assertEquals(1, result);
    }

    @Test
    public void readTasks_withoutFileExisting_throwsException() throws IOException {
        try {
            ArrayList<Task> result = new Storage("testData/tasks.txt").readTasks();
            assertEquals(this.testArr.size(), result.size());
        } catch (IOException e) {
            assertEquals("Uh oh, the file/directory doesn't seem to exist. No worries, one will be created for you at the end of your conversation!", e.getMessage());
        }
    }
}

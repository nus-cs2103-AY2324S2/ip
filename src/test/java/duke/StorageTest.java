package duke;

import duke.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private TaskList taskList;
    private String path = "./data/testFile.txt";
    private Storage storage;

    @BeforeEach
    public void preTest() {
        this.taskList = new TaskList();
        taskList.addTask(new Todo("test"));
        this.storage = new Storage(path);
    }

    @Test
    public void writeTasks_validInputs_pass() throws FileNotFoundException {
        storage.writeTasks(this.taskList);
        File testFile = new File(this.path);
        Scanner s = new Scanner(testFile);
        String writtenTask = s.nextLine();
        s.close();
        assertEquals("T | 0 | test", writtenTask);
        testFile.delete();
    }
}

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import luke.exception.SaveFileCorruptedException;
import org.junit.jupiter.api.Test;

import luke.List;
import luke.Parser;
import luke.Storage;
import luke.Ui;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.ToDo;

/**
 * Turn off assertions to test.
 */
public class StorageTest {
    private String path = "./testStorage/data.txt";
    private Ui ui = new Ui();
    private Parser parser = new Parser();

    @Test
    public void testCreateFile() throws IOException {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        Storage storage = new Storage(path);
        assertEquals(file.exists(), true);
    }

    @Test
    public void testCreateDirectory() throws IOException {
        File file = new File(path);
        File directory = new File("./testStorage");
        if (file.exists()) {
            file.delete();
        }
        if (directory.exists()) {
            directory.delete();
        }
        Storage storage = new Storage(path);
        assertEquals(directory.exists(), true);
    }

    @Test
    public void testCreateDirectoryAndFile() throws IOException {
        File file = new File(path);
        File directory = new File("./testStorage");
        if (file.exists()) {
            file.delete();
        }
        if (directory.exists()) {
            directory.delete();
        }
        Storage storage = new Storage(path);
        assertEquals(file.exists(), true);
    }

    @Test
    public void testSaveTasks() throws IOException {
        Storage storage = new Storage(path);
        storage.clearData();
        List taskList = new List(new ArrayList<>());
        taskList.addTask(new ToDo("eat"), storage, ui);
        taskList.addTask(new Deadline("homework", parser.toLocalDateTime("2024-02-02 23:59")), storage, ui);
        taskList.markTask(0, storage, ui);
        taskList.addTask(new Event("study", parser.toLocalDateTime("2024-02-02 08:00"),
                parser.toLocalDateTime("2024-02-02 12:00")), storage, ui);
        String expected = "1.[T][X] eat\n"
                + "2.[D][ ] homework (by: 02 Feb 2024 23:59)\n"
                + "3.[E][ ] study (from: 02 Feb 2024 08:00 to: 02 Feb 2024 12:00)";
        StringBuilder newFileContent = new StringBuilder();
        storage.saveTasks(taskList);
        BufferedReader reader = new BufferedReader(new FileReader(path));
        for (int i = 0; i < 2; i++) {
            newFileContent.append(reader.readLine()).append("\n");
        }
        newFileContent.append(reader.readLine());
        assertEquals(expected, String.valueOf(newFileContent));
    }

    @Test
    public void testLoadTasks() throws IOException, SaveFileCorruptedException {
        Storage storage = new Storage(path);
        storage.clearData();
        List taskList = new List(new ArrayList<>());
        taskList.addTask(new ToDo("eat"), storage, ui);
        taskList.addTask(new Deadline("homework", parser.toLocalDateTime("2024-02-02 23:59")), storage, ui);
        taskList.markTask(0, storage, ui);
        taskList.addTask(new Event("study", parser.toLocalDateTime("2024-02-02 08:00"),
                parser.toLocalDateTime("2024-02-02 12:00")), storage, ui);
        String newFileContent = "1.[T][X] eat\n"
                + "2.[D][ ] homework (by: 02 Feb 2024 23:59)\n"
                + "3.[E][ ] study (from: 02 Feb 2024 08:00 to: 02 Feb 2024 12:00)";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(newFileContent);
        writer.close();
        storage.loadTasks();
        assertEquals(newFileContent, taskList.toString());
    }
}

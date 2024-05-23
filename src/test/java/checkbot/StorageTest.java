package checkbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import checkbot.task.Todo;
import checkbot.task.TodoList;

public class StorageTest {
    @Test
    public void saveToFile_success() throws Exception {
        Storage storage = new Storage("./taskstest.txt");
        TodoList todoList = new TodoList();
        todoList.addTask(new Todo("test task"));
        storage.saveTasks(todoList);

        File f = new File("./taskstest.txt");
        f.deleteOnExit();
        Scanner scanner = new Scanner(f);
        String testString = scanner.nextLine();
        scanner.close();
        assertEquals(testString, "T | 0 | test task");
    }

    @Test
    public void loadFile_success() throws Exception {
        Writer writer = new FileWriter("./taskstest.txt");
        writer.write("T | 0 | test task");
        writer.close();
        File f = new File("./taskstest.txt");
        f.deleteOnExit();

        Storage storage = new Storage("./taskstest.txt");
        TodoList todoList = storage.loadTasks();
        assertEquals(todoList.getTask(0).formatForFile(), "T | 0 | test task");
    }
}

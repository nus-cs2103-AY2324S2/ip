package chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void testSaveAndLoadTasks() {
        Storage storage = new Storage("data/test.txt");
        TaskList taskList = new TaskList();
        taskList.addTaskFromInput("todo read book");
        try {
            storage.updateData(taskList);
        } catch (AlfredException e) {
            System.out.println(e.getMessage());
        }
        TaskList loadedTaskList = new TaskList();
        try {
            loadedTaskList = storage.processData(loadedTaskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        assertEquals(1, loadedTaskList.taskList.size());
        assertEquals("read book", loadedTaskList.taskList.get(0).getDescription());
    }

    @Test
    public void testSaveAndLoadMultipleTaskTypes() {
        Storage storage = new Storage("data/test2.txt");
        TaskList taskList = new TaskList();
        taskList.addTaskFromInput("todo read book");
        taskList.addTaskFromInput("deadline return book /by 01/12/2022 1800");
        taskList.addTaskFromInput("event book club /from 01/12/2022 1800 /to 01/12/2022 2000");
        try {
            storage.updateData(taskList);
        } catch (AlfredException e) {
            System.out.println(e.getMessage());
        }
        TaskList loadedTaskList = new TaskList();
        try {
            loadedTaskList = storage.processData(loadedTaskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        assertEquals(3, loadedTaskList.taskList.size());
        assertEquals("read book", loadedTaskList.taskList.get(0).getDescription());
        assertEquals("return book", loadedTaskList.taskList.get(1).getDescription());
        assertEquals("book club", loadedTaskList.taskList.get(2).getDescription());
    }
}
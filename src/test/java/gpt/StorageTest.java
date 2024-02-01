package gpt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;


import java.io.IOException;
import java.util.ArrayList;

import static gpt.Storage.createFolderAndFile;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    static String testFilePath = "./data/GPT.txt";
    @Test
    public void testStorageSaveAndLoadTasks() {

        try{
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Task task1 = new Task("Sample Task", TaskType.E, false, "01/01/2021 1800", "01/01/2021 2000");
            taskList.addTask(task1);
            Task task2 = new Task("Sample Task 2", TaskType.T, false);
            taskList.addTask(task2);
            Storage storage = new Storage(testFilePath);
            storage.saveTasks(taskList);

            TaskList loadedTaskList = storage.loadTasks();
            assertNotNull(loadedTaskList);
            assertEquals(taskList.size(), loadedTaskList.size());

            for (int i = 0; i < taskList.size(); i++) {
                Task originalTask = taskList.get(i);
                Task loadedTask = loadedTaskList.get(i);
                assertEquals(originalTask.getName(), loadedTask.getName());
                assertEquals(originalTask.isDone(), loadedTask.isDone());
                assertEquals(originalTask.getTaskType(), loadedTask.getTaskType());
                if (originalTask.getTaskType() == TaskType.E) {
                    assertEquals(originalTask.getStartDateString(), loadedTask.getStartDateString());
                    assertEquals(originalTask.getEndDateString(), loadedTask.getEndDateString());
                }
            }
        } catch (Exception e) {
            fail("Error creating data folder and file: " + e.getMessage());
        }




    }
}
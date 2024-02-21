package paimon.storage;

import paimon.exception.ChatBotParameterException;
import paimon.task.TaskList;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void createSaveDataFromTaskList_normalTask_success() throws ChatBotParameterException {
        Storage storage = new Storage("test.txt");
        String testCase = "E | 1 | hello | tmr | weekend\n" +
                "D | 0 | deadline | 2002-09-05T16:00\n" +
                "T | 0 | 1\n";
        TaskList taskListToBeTested = new TaskList();
        taskListToBeTested.addEvent("hello", "tmr", "weekend", true);
        taskListToBeTested.addDeadline("deadline", "5/9/2002 1600", false);
        taskListToBeTested.addToDo("1", false);
        assertEquals(testCase, storage.createSaveDataFromTaskList(taskListToBeTested).toString());
    }

    @Test
    void getFileLocationPath_normalFileName_success() {
        Storage storage = new Storage("test.txt");
        assertEquals(Paths.get("data","test.txt"), storage.getFileLocationPath());
    }


}
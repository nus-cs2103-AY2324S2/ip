package ukecat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void addTask_validInput_success() {
        // checks if task is added successfully
        Storage.setTaskCount(0);
        Storage.setInput("todo buy bread");
        Storage.setWords(new String[]{"todo", "buy bread"});
        System.out.println(Storage.getTaskCount());
        Storage.addTask();
        assertEquals(1, Storage.getTaskCount());
    }

    @Test
    public void addTask_invalidInput_exceptionThrown() {
        // throws exception if input not correct format
        Storage.setTaskCount(0);
        Storage.setInput("buy bread");
        Storage.setWords(new String[]{"todo", "buy bread"});
        System.out.println(Storage.getTaskCount());
        Storage.addTask();
        assertEquals(0, Storage.getTaskCount());
    }
}

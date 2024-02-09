package ukecat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void addTask_validInput_success() {
        // checks if task is added successfully
        Storage.taskCount = 0;
        Storage.input = "todo buy bread";
        Storage.words = new String[]{"todo", "buy bread"};
        System.out.println(Storage.taskCount);
        Storage.addTask();
        assertEquals(1, Storage.taskCount);
    }

    @Test
    public void addTask_invalidInput_exceptionThrown() {
        // throws exception if input not correct format
        Storage.taskCount = 0;
        Storage.input = "buy bread";
        Storage.words = new String[]{"todo", "buy bread"};
        System.out.println(Storage.taskCount);
        Storage.addTask();
        assertEquals(0, Storage.taskCount);
    }
}

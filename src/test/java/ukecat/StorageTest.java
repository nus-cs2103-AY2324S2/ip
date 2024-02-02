package ukecat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void addTask_validInput_success() {
        // checks if task is added successfully
        Storage.numT = 0;
        Storage.input = "todo buy bread";
        Storage.words = new String[]{"todo", "buy bread"};
        System.out.println(Storage.numT);
        Storage.addTask();
        assertEquals(1, Storage.numT);
    }

    @Test
    public void addTask_invalidInput_exceptionThrown() {
        // throws exception if input not correct format
        Storage.numT = 0;
        Storage.input = "buy bread";
        Storage.words = new String[]{"todo", "buy bread"};
        System.out.println(Storage.numT);
        Storage.addTask();
        assertEquals(0, Storage.numT);
    }
}

package ukecat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void addTask_validInput_success() {
        // checks if task is added successfully
        Storage.setTaskCount(0);
        Storage.setInput("todo buy bread");
        Storage.setDesc("buy bread");
        Storage.setWords(new String[]{"todo", "buy bread"});
        assertEquals("  Task added: [T][ ] buy bread" + System.lineSeparator()
                + "  You have 1 task in the list now.", Storage.addTask());
    }

    @Test
    public void addTask_invalidInput_exceptionThrown() {
        // throws exception if input not correct format
        Storage.setTaskCount(0);
        Storage.setInput("buy bread");
        Storage.setDesc("buy bread");
        Storage.setWords(new String[]{"todo", "buy bread"});
        System.out.println(Storage.getTaskCount());
        assertEquals("Error adding task: Wrong format, use: todo <desc>", Storage.addTask());
    }
}

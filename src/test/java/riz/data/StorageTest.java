package riz.data;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void storageLoadTest() {
        Storage storage = new Storage("riz/data/parsertest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        ToDo initial = new ToDo("shower");
        tasks.add(initial);
        storage.writeToFile(tasks);
        ToDo result = (ToDo) storage.loadFromFile().get(0);
        assertEquals(initial.toString(), result.toString());
    }
}

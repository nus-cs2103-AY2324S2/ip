package riz.data;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void storageLoadTest() {
        Storage storage = new Storage("riz/data/parsertest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        ToDo firstTask = new ToDo("shower");
        Deadline secondTask = new Deadline("Assignment", "16/06/2001 2359");
        Event thirdTask = new Event("Staycation", "17/06/2022 1500", "19/06/2022 1200");
        tasks.add(firstTask);
        tasks.add(secondTask);
        tasks.add(thirdTask);
        storage.writeToFile(tasks);
        for (int i = 0; i < 3; i++) {
            Task result = storage.loadFromFile().get(i);
            System.out.println(result);
            assertEquals(tasks.get(i).toString(), result.toString());
        }
    }
}

package dude;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.Todo;

public class StorageTest {
    @Test
    public void storage_path_noException() {
        Storage storage = new Storage("test");
        assertEquals(false, Parser.isNumeric("a"));
    }

    @Test
    public void createRows_taskList_fileWritten() {
        Storage storage = new Storage("test");
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("test1"));
        taskList.add(new Deadline("test2", "2022-01-01"));
        taskList.add(new Event("test3", "2022-02-02", "2022-03-03"));
        storage.createRows(taskList);
        String[] expected = new String[]{
            "T | 0 | test1",
            "D | 0 | test2 | 2022-01-01",
            "E | 0 | test3 | 2022-02-02 | 2022-03-03"
        };

        try {
            File f = new File("./data/test.txt"); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            int i = 0;
            while (s.hasNext()) {
                String line = s.nextLine();
                assertEquals(expected[i], line);
                i++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred reading from the storage file.");
            assertEquals(0, 1);
        }
    }
}

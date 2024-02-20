package oop;

import lemona.oop.Storage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import lemona.task.Task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
public class StorageTest {

    @Test
    public void stringToList_givenString_success(){
        ArrayList<Task> tasks = new ArrayList<>();
        String input = "[T] / [ ] / vitamin c";
        Storage storage = new Storage("data/lemona.txt");
        tasks = storage.stringToList(tasks, input);
        assertEquals("[T][ ] vitamin c", tasks.get(0).print());
    }

    @Test void stringToList_dateTimeParse_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        String input = "[D] / [ ] / vitamin c / 1/1/1";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Storage storage = new Storage("data/lemona.txt");
        tasks = storage.stringToList(tasks, input);
        System.setOut(System.out);
        String actualOutput = outContent.toString().trim();
        String output = "I think you haven't had enough vitamin C."
                + "\nYour time format should be :"
                + "\n\t{ dd/MM/yyyy HHmm }"
                + "\nI suggest you take some LEMONA.";

        assertEquals(output.trim(), actualOutput.replace("\r\n", "\n"));
    }
}

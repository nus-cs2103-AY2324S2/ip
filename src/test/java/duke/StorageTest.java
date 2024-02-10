package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class StorageTest {
    //testing savetofile method
    @Test
    public void testSaveToFile() throws IOException {
        String tempFilePath = System.getProperty("java.io.tmpdir") + "/duke_test.txt";
        ArrayList<Task> tasks = new ArrayList<>(Arrays.asList(
                new Task("read book", Task.TaskType.TODO),
                new Task("do assignment", Task.TaskType.DEADLINE),
                new Task("midterm", Task.TaskType.EVENT)
        ));

        Storage storage = new Storage(tempFilePath);

        storage.saveToFile(tasks.size(), tasks);

        List<String> output = Files.readAllLines(Path.of(tempFilePath));
        List<String> expected = Arrays.asList(
                "[T][ ] read book",
                "[D][ ] do assignment",
                "[E][ ] midterm"
        );

        assertLinesMatch(expected, output);

        Files.deleteIfExists(Path.of(tempFilePath));


    }
    }




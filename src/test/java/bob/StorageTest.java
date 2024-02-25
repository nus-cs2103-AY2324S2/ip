package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import bob.exception.BobException;
import bob.task.Task;
import bob.task.TaskStub;

public class StorageTest {
    @TempDir
    Path sharedTempDir;

    @Test
    public void load_validDataFile_success() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");
        String[] fileContents = new String[] {
            "T | false | a",
            "T | true | a",
            "D | false | a | 2024-02-12T23:12:00",
            "D | true | a | 2024-02-12T23:12:00",
            "E | false | a | 2024-02-11T23:12:00 | 2024-02-12T23:12:00",
            "E | true | a | 2024-02-11T23:12:00 | 2024-02-12T23:12:00"
        };
        String[] expectedTasks = new String[] {
            "[T][ ] a",
            "[T][X] a",
            "[D][ ] a (by: Feb 12 2024 2312)",
            "[D][X] a (by: Feb 12 2024 2312)",
            "[E][ ] a (from: Feb 11 2024 2312 to: Feb 12 2024 2312)",
            "[E][X] a (from: Feb 11 2024 2312 to: Feb 12 2024 2312)"
        };

        BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath.toFile().getAbsoluteFile()));
        for (String fileContent : fileContents) {
            bw.write(fileContent);
            bw.newLine();
        }
        bw.flush();
        bw.close();

        try {
            ArrayList<Task> tasks = new Storage().load(dataPath.toString());
            for (int i = 0; i < 6; i++) {
                assertEquals(expectedTasks[i], tasks.get(i).toString());
            }
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void load_invalidStorageIndicator_exceptionThrown() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath.toFile().getAbsoluteFile()));
        bw.write("A | false | a");
        bw.flush();
        bw.close();

        try {
            new Storage().load(dataPath.toString());
            fail();
        } catch (BobException e) {
            assertEquals("invalid storage indicator detected", e.getMessage());
        }
    }

    @Test
    public void load_invalidIsDone_exceptionThrown() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath.toFile().getAbsoluteFile()));
        bw.write("T | 1 | a");
        bw.flush();
        bw.close();

        try {
            new Storage().load(dataPath.toString());
            fail();
        } catch (BobException e) {
            assertEquals("invalid value for isDone detected", e.getMessage());
        }
    }

    @Test
    public void load_generalInvalid_exceptionThrown() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath.toFile().getAbsoluteFile()));
        bw.write("T | true");
        bw.flush();
        bw.close();

        try {
            new Storage().load(dataPath.toString());
            fail();
        } catch (BobException e) {
            assertEquals("Index 2 out of bounds for length 2", e.getMessage());
        }
    }

    @Test
    public void saveTask() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        try {
            Storage storage = new Storage();
            storage.load(dataPath.toString());

            Task task = new TaskStub("a");
            storage.saveTask(task);

            Scanner s = new Scanner(dataPath.toFile());
            assertEquals("storage format", s.nextLine());
            s.close();
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void refresh() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        String[] fileContents = new String[] {
            "T | false | a",
            "T | true | a",
            "D | false | a | 2024-02-12T23:12:00",
            "D | true | a | 2024-02-12T23:12:00",
            "E | false | a | 2024-02-11T23:12:00 | 2024-02-12T23:12:00",
            "E | true | a | 2024-02-11T23:12:00 | 2024-02-12T23:12:00"
        };

        ArrayList<Task> tasks = new ArrayList<>(Arrays.asList(
                new TaskStub("a"),
                new TaskStub("a"),
                new TaskStub("a")));

        BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath.toFile().getAbsoluteFile()));
        for (String fileContent : fileContents) {
            bw.write(fileContent);
            bw.newLine();
        }
        bw.flush();
        bw.close();

        try {
            Storage storage = new Storage();
            storage.load(dataPath.toString());
            storage.refresh(tasks);

            Scanner s = new Scanner(dataPath.toFile());
            while (s.hasNext()) {
                assertEquals("storage format", s.nextLine());
            }
            s.close();
        } catch (BobException e) {
            fail();
        }
    }
}

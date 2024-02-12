package bob;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import bob.exception.BobException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

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
    public void saveTask_todoUndone_success() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        try {
            Storage storage = new Storage();
            storage.load(dataPath.toString());

            storage.saveTask(new Todo("a"));

            Scanner s = new Scanner(dataPath.toFile());
            assertEquals("T | false | a", s.nextLine());
            s.close();
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void saveTask_todoDone_success() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        try {
            Storage storage = new Storage();
            storage.load(dataPath.toString());

            Todo todo = new Todo("a");
            todo.setDone(true);
            storage.saveTask(todo);

            Scanner s = new Scanner(dataPath.toFile());
            assertEquals("T | true | a", s.nextLine());
            s.close();
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void saveTask_deadlineUndone_success() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        try {
            Storage storage = new Storage();
            storage.load(dataPath.toString());

            storage.saveTask(new Deadline("a",
                    LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0)));

            Scanner s = new Scanner(dataPath.toFile());
            assertEquals("D | false | a | 2024-02-12T19:37:00", s.nextLine());
            s.close();
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void saveTask_deadlineDone_success() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        try {
            Storage storage = new Storage();
            storage.load(dataPath.toString());

            Deadline deadline = new Deadline("a",
                    LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
            deadline.setDone(true);
            storage.saveTask(deadline);

            Scanner s = new Scanner(dataPath.toFile());
            assertEquals("D | true | a | 2024-02-12T19:37:00", s.nextLine());
            s.close();
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void saveTask_eventUndone_success() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        try {
            Storage storage = new Storage();
            storage.load(dataPath.toString());

            storage.saveTask(new Event("a",
                    LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                    LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0)));

            Scanner s = new Scanner(dataPath.toFile());
            assertEquals("E | false | a | 2024-02-11T19:37:00 | 2024-02-12T19:37:00", s.nextLine());
            s.close();
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void saveTask_eventDone_success() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        try {
            Storage storage = new Storage();
            storage.load(dataPath.toString());

            Event event = new Event("a",
                    LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                    LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
            event.setDone(true);
            storage.saveTask(event);

            Scanner s = new Scanner(dataPath.toFile());
            assertEquals("E | true | a | 2024-02-11T19:37:00 | 2024-02-12T19:37:00", s.nextLine());
            s.close();
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void testRefresh() throws IOException {
        Path dataPath = sharedTempDir.resolve("bob.txt");

        ArrayList<Task> tasks = new ArrayList<>(Arrays.asList(
                new Todo("a"),
                new Todo("a"),
                new Deadline("a",
                        LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0)),
                new Deadline("a",
                        LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0)),
                new Event("a",
                        LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                        LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0)),
                new Event("a",
                        LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                        LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
        ));

        tasks.get(1).setDone(true);
        tasks.get(3).setDone(true);
        tasks.get(5).setDone(true);

        String[] expected = new String[] {
                "T | false | a",
                "T | true | a",
                "D | false | a | 2024-02-12T19:37:00",
                "D | true | a | 2024-02-12T19:37:00",
                "E | false | a | 2024-02-11T19:37:00 | 2024-02-12T19:37:00",
                "E | true | a | 2024-02-11T19:37:00 | 2024-02-12T19:37:00"
        };

        try {
            Storage storage = new Storage();
            storage.load(dataPath.toString());
            storage.refresh(tasks);

            Scanner s = new Scanner(dataPath.toFile());
            for (int i = 0; s.hasNext(); i++) {
                assertEquals(expected[i], s.nextLine());
            }
            s.close();
        } catch (BobException e) {
            fail();
        }
    }
}

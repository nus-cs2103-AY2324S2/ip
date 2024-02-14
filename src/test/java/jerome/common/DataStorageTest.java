package jerome.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jerome.exception.MalformedUserInputException;
import jerome.tasklist.Deadline;
import jerome.tasklist.Event;
import jerome.tasklist.Priority;
import jerome.tasklist.Task;
import jerome.tasklist.Todo;

/**
 * Tests the functionality of the {@code DataStorage} class.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * error handling and output.
 */
public class DataStorageTest {
    // Represents the String that the Tests format of the database.
    private static final String TODO_DATABASE_FORMAT = "T | %s | %s | %s";
    private static final String DEADLINE_DATABASE_FORMAT = "D | %s | %s | %s | %s";
    private static final String EVENT_DATABASE_FORMAT = "E | %s | %s | %s | %s | %s";

    // Represents the random activities and valid timestamps used for testing.
    private static final String[] activityNames = {"Running", "Swimming", "Cycling", "Hiking", "Skiing"};
    private static final String[] datesRange = {"2024-01-01", "2025-01-01", "2024-03-21", "2024-12-31", "2023-01-01"};

    private static final int NUMBER_OF_EVENTS = 200;

    /**
     * Creates a temporary file used internally for Tests.
     */
    private File mockFile;

    /**
     * Creates the dataStorage that we are testing.
     */
    private DataStorage dataStorage;

    private ArrayList<Task> tasks;


    /**
     * Returns the total number of lines in the mockFile.
     *
     * @return the line count of the temporary file.
     * @throws RuntimeException if the file has any errors.
     */
    private int getLineCount() {
        int count = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(mockFile));
            while (bufferedReader.readLine() != null) {
                count++;
            }
            return count;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }


    /**
     * Returns the line in String at specified index in the test txt file.
     *
     * @param index the index of the line to be retrieved.
     * @return the line at the specified index.
     * @throws RuntimeException if the file has any errors.
     */
    private String getLine(int index) {
        int count = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(mockFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                count++;
                if (count == index) {
                    return line;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }

        throw new RuntimeException("not in specified index!");
    }

    /**
     * Sets up the test environment before each test case.
     * It creates a temporary file, initializes the DataStorage object used for testing
     * and creates tasks.
     *
     * Temporary file is deleted after testing.
     *
     * @throws MalformedUserInputException if there is an error in the user input.
     */
    @BeforeEach
    public void setUp() throws MalformedUserInputException {
        // Create a temporary file
        this.tasks = new ArrayList<>();

        try {
            // Referenced from:
            // https://stackoverflow.com/questions/26860167/what-is-a-safe-way-to-create-a-temp-file-in-java
            mockFile = Files.createTempFile("test", ".txt").toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mockFile.deleteOnExit();

        // Initialize DataStorage with the temporary file
        dataStorage = new DataStorage(10000, mockFile.getAbsolutePath());

        System.out.println(mockFile.getAbsolutePath());

        createTasks();
    }


    private void createTasks() throws MalformedUserInputException {
        for (int i = 0; i < NUMBER_OF_EVENTS; i++) {
            Task task;
            if (i % 3 == 0) {
                task = new Todo(getRandomEvent(), false);
            } else if (i % 3 == 1) {
                task = new Deadline(getRandomEvent(), getRandomDate(), false);
            } else {
                task = new Event(getRandomEvent(), getRandomDate(), getRandomDate(), false);
            }

            tasks.add(task);
            dataStorage.addTaskToTextFile(task);
        }
    }

    /**
     * Check if the number of tasks stored in database increases.
     */
    @Test
    public void dataStorage_task_increaseAfterAdding() {
        Todo sampleTask = new Todo("Sample Task", false);
        // Get line count before we add
        int currentLineCount = this.getLineCount();
        dataStorage.addTaskToTextFile(sampleTask);
        assertEquals(dataStorage.getTaskCount(), currentLineCount + 1);
    }

    /**
     * Checks if the task is properly stored.
     *
     * @throws RuntimeException if the file has any errors.
     */
    @Test
    public void dataStorage_task_correctlyStored() {
        String taskName = "Sample Task";
        boolean isDone = true;
        Priority priority = Priority.LOW;

        Todo sampleTask = new Todo(taskName, isDone, priority);

        String databaseEntry = String.format(TODO_DATABASE_FORMAT, taskName, isDone, priority);
        dataStorage.addTaskToTextFile(sampleTask);
        int currentLineCount = this.getLineCount();
        assertEquals(this.getLine(currentLineCount), databaseEntry);
    }


    /**
     * Checks if deadline with the correct format is stored.
     * @throws MalformedUserInputException if the date input is dirty.
     */

    @Test
    public void dataStorage_deadline_correctlyStored() throws MalformedUserInputException {
        String taskName = "Sample Task";
        String deadline = "2024-01-02";
        boolean isDone = true;
        Priority priority = Priority.HIGH;


        Deadline sampleTask = new Deadline(taskName, deadline, isDone, priority);

        String databaseEntry = String.format(DEADLINE_DATABASE_FORMAT, taskName, isDone, deadline, priority);
        dataStorage.addTaskToTextFile(sampleTask);
        int currentLineCount = this.getLineCount();
        assertEquals(this.getLine(currentLineCount), databaseEntry);
    }


    /**
     * Tests if the event in the data storage is correctly marked as completed
     * by comparing the String in test txt file and actual txt file.
     *
     * @throws MalformedUserInputException if there is an error in the user input.
     */
    @Test
    public void dataStorage_event_correctlyMarked() throws MalformedUserInputException {
        int currentLineCount = this.getLineCount();
        int indexToDelete = getRandomNumberUsingNextInt(1, currentLineCount);
        Task toDelete = tasks.get(indexToDelete);

        String previousRecord = toDelete.toString();
        dataStorage.setTaskStatus(indexToDelete, true);
        assertNotEquals(previousRecord, dataStorage.getTask(indexToDelete).toString());
    }

    /**
     * Tests deletion of a task from the task list at a random valid index
     * which is between zero and some positive integer.
     *
     * @throws MalformedUserInputException if unable to delete.
     */
    @Test
    public void dataStorage_event_correctlyDeleted() throws MalformedUserInputException {
        int currentLineCount = this.getLineCount();
        int indexToDelete = getRandomNumberUsingNextInt(1, currentLineCount);
        dataStorage.deleteTask(indexToDelete);
        assertEquals(dataStorage.getTaskCount(), currentLineCount - 1);
    }

    /**
     * Tests if an event is properly stored.
     * @throws MalformedUserInputException if unable to add simple task.
     */
    @Test
    public void dataStorage_event_correctlyStored() throws MalformedUserInputException {
        String taskName = "Sample Task";
        String startTime = "2024-01-02";
        String endTime = "2024-01-02";
        boolean isDone = true;
        Priority priority = Priority.MEDIUM;

        Event sampleTask = new Event(taskName, startTime, endTime, isDone);

        String databaseEntry = String.format(EVENT_DATABASE_FORMAT, taskName, isDone, startTime, endTime, priority);
        dataStorage.addTaskToTextFile(sampleTask);
        int currentLineCount = this.getLineCount();
        assertEquals(this.getLine(currentLineCount), databaseEntry);
    }

    /**
     * Returns a random integer between the specified minimum and maximum values (inclusive).
     *
     * @param min the lower bound of the range.
     * @param max the upper bound of the range.
     * @return a random integer within the specified range.
     *     @@author baeldung.
     *     Reuse from https://www.baeldung.com/java-generating-random-numbers-in-range
     *     with no modifications.
     */
    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * Returns a random event from the activityNames array.
     *
     * @return the names of a random event.
     */
    public String getRandomEvent() {
        int index = getRandomNumberUsingNextInt(0, activityNames.length);
        return activityNames[index];
    }

    /**
     * Returns a random valid date from the datesRange array.
     *
     * @return a random valid date as a String.
     */
    public String getRandomDate() {
        int index = getRandomNumberUsingNextInt(0, datesRange.length);
        return datesRange[index];
    }

    /**
     * Tests marking a task at a very large index as completed,
     * which is expected to throw an Exception.
     */
    @Test
    @AfterEach
    public void dataStorage_task_markVeryLargeIndexAsCompleted() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            dataStorage.setTaskStatus(1000000000, true);
        });
    }
}

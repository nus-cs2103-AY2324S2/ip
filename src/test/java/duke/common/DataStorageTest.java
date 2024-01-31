package duke.common;

import duke.exception.MalformedUserInputException;
import duke.tasklist.Deadline;
import duke.tasklist.Event;
import duke.tasklist.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataStorageTest {

    private File mockFile;
    private DataStorage dataStorage;
    private static final String NON_EXISTENT_FILE_NAME = "This_IS_a_BaD_FiKeName.txt";
    private static final String TODO_DATABASE_FORMAT = "T | %s | %s";
    private static final String DEADLINE_DATABASE_FORMAT = "D | %s | %s | %s";
    private static final String EVENT_DATABASE_FORMAT = "E | %s | %s | %s | %s";


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

    @BeforeEach
    public void setUp() {
        // Create a temporary file
        try {
            // Referenced from: https://stackoverflow.com/questions/26860167/what-is-a-safe-way-to-create-a-temp-file-in-java
            mockFile = Files.createTempFile("test", ".txt").toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mockFile.deleteOnExit();

        // Initialize DataStorage with the temporary file
        dataStorage = new DataStorage(10, mockFile.getAbsolutePath());

        System.out.println(mockFile.getAbsolutePath());
    }

    /**
     * Check if the number of tasks stored in database increases.
     */
    @Test
    public void dataStorage_task_increaseAfterAdding() {
        Todo sampleTask = new Todo("Sample Task", false);
        // Get line count before we add
        int currentLineCount = this.getLineCount();
        dataStorage.addTask(sampleTask);
        assertEquals(dataStorage.getTaskCount(), currentLineCount + 1);
    }

    @Test
    public void dataStorage_task_correctlyStored() {
        String taskName = "Sample Task";
        boolean isDone = true;

        Todo sampleTask = new Todo(taskName, isDone);

        String databaseEntry = String.format(TODO_DATABASE_FORMAT, taskName, isDone);
        dataStorage.addTask(sampleTask);
        int currentLineCount = this.getLineCount();
        assertEquals(this.getLine(currentLineCount), databaseEntry);
    }


    @Test
    public void dataStorage_deadline_correctlyStored() throws MalformedUserInputException {
        String taskName = "Sample Task";
        String deadline = "2024-01-02";
        boolean isDone = true;

        Deadline sampleTask = new Deadline(taskName, deadline, isDone);

        String databaseEntry = String.format(DEADLINE_DATABASE_FORMAT, taskName, isDone, deadline);
        dataStorage.addTask(sampleTask);
        int currentLineCount = this.getLineCount();
        assertEquals(this.getLine(currentLineCount), databaseEntry);
    }


    @Test
    public void dataStorage_event_correctlyStored() throws MalformedUserInputException {
        String taskName = "Sample Task";
        String startTime = "2024-01-02";
        String endTime = "2024-01-02";
        boolean isDone = true;

        Event sampleTask = new Event(taskName, startTime, endTime, isDone);

        String databaseEntry = String.format(EVENT_DATABASE_FORMAT, taskName, isDone, startTime, endTime);
        dataStorage.addTask(sampleTask);
        int currentLineCount = this.getLineCount();
        assertEquals(this.getLine(currentLineCount), databaseEntry);
    }



    @Test
    @AfterEach
    public void dataStorage_task_markVeryLargeIndexAsCompleted() throws MalformedUserInputException {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            dataStorage.setTaskStatus(1000000000, true);
        });
    }
}

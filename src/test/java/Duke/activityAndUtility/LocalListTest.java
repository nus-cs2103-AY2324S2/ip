package Duke.activityAndUtility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The {@code LocalListTest} class contains unit tests for the {@code LocalList} class, verifying its ability to
 * correctly save and load {@link Activity} objects to and from a file. It tests the functionality with valid data
 * and also checks the behavior when encountering invalid file paths.
 */
public class LocalListTest {

    private LocalList localList;
    private ArrayList<Activity> activities;
    String tempDir;

    /**
     * Sets up the test environment before each test. This includes creating a {@code LocalList} instance
     * with a temporary directory for testing file operations.
     */
    @BeforeEach
    public void setUp() {
        tempDir = "duke.txt";
        localList = new LocalList(tempDir);
    }

    /**
     * Tests the ability of the {@code LocalList} to save a list of activities to a file.
     * Verifies that the file contains the correct representations of the activities after saving.
     *
     * @throws IOException if an I/O error occurs during file writing or reading.
     */
    @Test
    public void testSaveActivities() throws IOException {
        activities = new ArrayList<>();
        activities.add(new Todo("√", "Todo task"));
        activities.add(new Deadline("X", "Deadline task", "2024-10-01 1400"));
        activities.add(new Event("X", "Event task", "2024-10-01 1400", "2024-10-01 1600"));
        // Save activities to file
        localList.save(activities);

        // Read file content
        File file = new File(tempDir);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        // Verify the file content
        assertTrue(lines.contains("T | √ | Todo task"),
                "File should contain the marked saved Todo task.");
        assertTrue(lines.contains("D | X | Deadline task | 2024-10-01 1400"),
                "File should contain the saved Deadline task.");
        assertTrue(lines.contains("E | X | Event task | 2024-10-01 1400 | 2024-10-01 1600"),
                "File should contain the saved Event task.");
    }

    /**
     * Tests the behavior of the {@code LocalList} when attempting to load activities from an invalid file path.
     * Expects an {@code IOException} to be thrown to indicate the file cannot be accessed.
     */
    @Test
    public void testInvalidFile() {
        activities = new ArrayList<>();
        String expectedMessage = "Finish date ahead of start date";
        IOException thrown = assertThrows(IOException.class, ()-> {
            new LocalList("./smw/ImNotThere.txt").load();
            throw new IOException("File is not there or has been corrupted");
        }, "The expected exception was not thrown.");
    }

    /**
     * Cleans up the test environment after each test. This includes clearing the list of activities and ensuring
     * the test file is reset, preventing test data persistence between tests.
     */
    @AfterEach
    public void tearDown() {
        if (!activities.isEmpty()) {
            activities.clear();
            localList.save(activities);
        }
    }
}


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

public class LocalListTest {

    private LocalList localList;
    private ArrayList<Activity> activities;
    String tempDir;

    @BeforeEach
    public void setUp() {
        tempDir = "duke.txt";
        localList = new LocalList(tempDir);
    }

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

    @Test
    public void testInvalidFile() {
        activities = new ArrayList<>();
        String expectedMessage = "Finish date ahead of start date";
        IOException thrown = assertThrows(IOException.class, ()-> {
            new LocalList("./smw/ImNotThere.txt").load();
            throw new IOException("File is not there or has been corrupted");
        }, "The expected exception was not thrown.");
    }

    @AfterEach
    public void tearDown() {
        if (!activities.isEmpty()) {
            activities.clear();
            localList.save(activities);
        }
    }
}


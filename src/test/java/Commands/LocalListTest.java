package Commands;

import Duke.Activities.ActivityList;
import Duke.LocalStorage.LocalList;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;


public class LocalListTest {
    @Test
    public void testWriteAndRead() throws IOException {
        // Arrange
        String tempFilePath = "temp_test_file.txt";
        LocalList localList = new LocalList(tempFilePath);
        ActivityList activityList = new ActivityList(tempFilePath);

        // Create sample activities
        activityList.add("Task1");
        activityList.add("Task2");

        // Act
        localList.write(activityList.toStorageList());

        // Assert
        assertTrue(new File(tempFilePath).exists()); // File should exist after write

        // Act (read from file)
        ActivityList readActivityList = new ActivityList(tempFilePath);
        String read = readActivityList.printActivities();
        // Assert
        try (BufferedReader reader = new BufferedReader(new StringReader(read))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line here
                assertEquals("Task1", line);
            }
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }

        // Clean up: delete the temporary file
        Files.deleteIfExists(new File(tempFilePath).toPath());
    }
}

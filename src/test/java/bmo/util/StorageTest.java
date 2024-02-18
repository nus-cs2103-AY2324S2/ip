package bmo.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    void testLoadData() throws IOException {
        // Create a temporary file
        File tempFile = tempDir.resolve("task_data.txt").toFile();

        // Write some data to the temporary file
        FileWriter fileWriter = new FileWriter(tempFile);
        fileWriter.write("D | 1 | mario kart | 15/02/2024 1900\nT | 1 | lecture watching\n" +
                "E | 0 | cs2103t class | 15/02/2024 1700 | 15/02/2024 1800");
        fileWriter.close();

        // Mock the Storage class to use the temporary file
        Storage storage = Mockito.spy(new Storage());
        Mockito.doReturn(tempFile).when(storage).getDataFile();

        // Test the loadData method
        String loadedData = storage.loadData();
        assertEquals("D | 1 | mario kart | 15/02/2024 1900\nT | 1 | lecture watching\n" +
                "E | 0 | cs2103t class | 15/02/2024 1700 | 15/02/2024 1800", loadedData);
    }

    @Test
    void testSaveData() throws IOException {
        // Create a temporary file
        File tempFile = tempDir.resolve("task_data.txt").toFile();
        tempFile.createNewFile();

        // Mock the Storage class to use the temporary file
        Storage storage = Mockito.spy(new Storage());
        Mockito.doReturn(tempFile).when(storage).getDataFile();

        // Create a mock TaskList with some tasks
        TaskList mockTasks = mock(TaskList.class);
        when(mockTasks.toSaveData()).thenReturn("D | 1 | mario kart | 15/02/2024 1900\nT | 1 | lecture watching\n" +
                "E | 0 | cs2103t class | 15/02/2024 1700 | 15/02/2024 1800");

        // Test the saveData method
        storage.saveData(mockTasks, tempFile.getAbsolutePath());

        // Verify that the data was saved correctly
        Scanner sc = new Scanner(tempFile);
        StringBuilder loadedData = new StringBuilder();
        while (sc.hasNextLine()) {
            loadedData.append(sc.nextLine()).append("\n");
        }

        sc.close();

        assertEquals("D | 1 | mario kart | 15/02/2024 1900\nT | 1 | lecture watching\n" +
                "E | 0 | cs2103t class | 15/02/2024 1700 | 15/02/2024 1800\n", loadedData.toString());
    }
}


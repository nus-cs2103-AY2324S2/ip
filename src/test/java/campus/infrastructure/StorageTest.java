package campus.infrastructure;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    /**
     * Creates a new DataFile by initialising the storage object, passes the test case if the file is found in
     * the specified file path
     */
    @Test
    public void newDataFileInitialisation() {
        // Specify the path and name of the file to be checked
        String filePath = "src/test/java/campus/dataTest.txt";
        Storage storage = new Storage(filePath);
        Path path = Paths.get(filePath);
        assertTrue(Files.exists(path));
    }

    /**
     * Tests the Storage Object's ability to read data from the file assuming that it was read with certain inputs
     * and passes if the outputs are the same
     */
    @Test
    public void readCorrectDataFromFile() {
        try {
            // Data to be written to the file
            List<String> writeData = List.of("T | 0 | do homework",
                    "E | 0 | dinner with friends | 1900 29/01/2024 | 2200 29/01/2024",
                    "D | 0 | buy cny decorations | 2359 30/01/2024");

            String filePath = "src/test/java/campus/dataTest.txt";
            Path path = Paths.get(filePath);
            Files.write(path, writeData);
            
            Storage storage = new Storage(filePath);

            List<String> returnData = storage.getListOfStrings();
            assertEquals(writeData, returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

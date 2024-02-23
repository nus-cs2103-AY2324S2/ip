package oak.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUtilityTest {
    private static final String DIRPATH = "/src/test/java";
    private static final String FILENAME = "testFile.txt";
    private static final String FILE_CONTENT = "Test Content";
    private FileUtility fileUtility = new FileUtility();

    @BeforeEach
    public void before() throws IOException {
        File file = new File(System.getProperty("user.dir") + DIRPATH + "/" + FILENAME);

        // Create if not exists
        file.createNewFile();

        FileWriter fw = new FileWriter(file, false); // Overwrite values

        fw.write(FILE_CONTENT);
        fw.close();
    }

    @AfterAll
    public static void tearDown() {
        File file = new File(System.getProperty("user.dir") + DIRPATH + "/" + FILENAME);
        file.delete();
    }

    @Test
    public void loadFileLoadsTheCorrectContentTest() throws FileNotFoundException {
        ArrayList<String> fileContents = this.fileUtility.loadFile(DIRPATH + "/" + FILENAME);

        assertEquals(fileContents.size(), 1);
        assertEquals(fileContents.get(0), FILE_CONTENT);
    }

    @Test
    public void writeToFileTest() throws IOException {
        String testData = "Something";
        fileUtility.writeToFile(DIRPATH + "/" + FILENAME, testData);

        File file = new File(System.getProperty("user.dir") + DIRPATH + "/" + FILENAME);
        Scanner scanner = new Scanner(file);

        Boolean contentExists = false;
        int numLines = 0;

        while (scanner.hasNextLine()) {
            numLines++;
            if (scanner.nextLine().strip().equals(testData)) {
                contentExists = true;
            };
        }

        assertEquals(contentExists, true);
        assertEquals(numLines, 2);

        scanner.close();
    }
}

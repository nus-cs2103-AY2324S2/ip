package osiris.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for TxtFileStorage class.
 */
public class TxtFileStorageTest {

    /**
     * Test for checking if the storage file exists.
     */
    @Test
    public void storageFileExistTest() {
        TxtFileStorage storage = new TxtFileStorage("Test.txt");
        storage.initialiseTxtFileStorage();
        assertTrue(storage.storageFileExist());

        File testFile = new File("Test.txt");
        testFile.delete();
        assertFalse(storage.storageFileExist());
    }

    /**
     * Test for initializing the text file storage.
     */
    @Test
    public void initialiseTxtFileStorageTest() {
        TxtFileStorage storage = new TxtFileStorage("Test.txt");
        storage.initialiseTxtFileStorage();

        File testFile = new File("Test.txt");
        assertTrue(testFile.exists());
        testFile.delete();
    }

    /**
     * Test for reading content from the text file storage.
     */
    @Test
    public void readTxtFileStorageTest() {
        try {
            File testFile = new File("Test.txt");
            FileWriter fw = new FileWriter("Test.txt", true);
            ArrayList<String> testInputs = new ArrayList<>(Arrays.asList("ABC", "123"));
            for (String testStr: testInputs) {
                fw.write(testStr + "\n");
            }
            fw.close();

            TxtFileStorage storage = new TxtFileStorage("Test.txt");
            ArrayList<String> readContents = storage.readTxtFileStorage();
            boolean valid = true;
            int i = 0;

            if (testInputs.size() != readContents.size()) {
                valid = false;
            }

            while (valid && i < testInputs.size()) {
                if (!testInputs.get(i).equals(readContents.get(i))) {
                    valid = false;
                }
                i = i + 1;
            }
            assertTrue(valid);
            testFile.delete();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Test for appending content to the text file storage.
     */
    @Test
    public void appendToTxtFileStorageTest() {
        try {
            File testFile = new File("Test.txt");
            ArrayList<String> testInputs = new ArrayList<>(Arrays.asList("ABC", "123"));
            TxtFileStorage storage = new TxtFileStorage("Test.txt");
            for (String testInput: testInputs) {
                storage.appendToTxtFileStorage(testInput);
            }

            ArrayList<String> readContents = new ArrayList<>();
            Scanner scanner = new Scanner(testFile);
            while (scanner.hasNext()) {
                readContents.add(scanner.nextLine());
            }

            boolean valid = true;
            int i = 0;

            if (testInputs.size() != readContents.size()) {
                valid = false;
            }

            while (valid && i < testInputs.size()) {
                if (!testInputs.get(i).equals(readContents.get(i))) {
                    valid = false;
                }
                i = i + 1;
            }
            assertTrue(valid);
            testFile.delete();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            File testFile = new File("Test.txt");
            testFile.delete();
        }
    }

    /**
     * Test for clearing content from the text file storage.
     */
    @Test
    public void clearTxtFileStorage() {
        try {
            File testFile = new File("Test.txt");
            TxtFileStorage storage = new TxtFileStorage("Test.txt");
            FileWriter fw = new FileWriter("Test.txt", true);
            ArrayList<String> testInputs = new ArrayList<>(Arrays.asList("ABC", "123"));

            for (String testStr: testInputs) {
                fw.write(testStr + "\n");
            }
            fw.close();
            storage.clearTxtFileStorage();
            int i = 0;
            Scanner scanner = new Scanner(testFile);
            while (scanner.hasNext()) {
                i = i + 1;
                scanner.nextLine();
            }
            assert (i == 0);
            testFile.delete();
        } catch (IOException e) {
            System.err.println(e);
            File testFile = new File("Test.txt");
            testFile.delete();
        }
    }
}

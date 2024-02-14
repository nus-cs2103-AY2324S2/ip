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
public class StorageTxtFileTest {

    /**
     * Tests initialise storage file function.
     */
    @Test
    public void initialiseStorageTxtFile() {
        StorageTxtFile storage = new StorageTxtFile("Test.txt");
        storage.initialiseStorageTxtFile();

        File testFile = new File("Test.txt");
        assertTrue(testFile.exists());

        testFile.delete();
    }

    /**
     * Tests storage file exists function.
     */
    @Test
    public void storageFileExistTest() {
        try {
            File testFile = new File("Test.txt");
            testFile.createNewFile();

            StorageTxtFile storage = new StorageTxtFile("Test.txt");
            assertTrue(storage.doesStorageFileExist());

            testFile.delete();
            assertFalse(storage.doesStorageFileExist());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Tests reading content from the text file storage function.
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

            StorageTxtFile storage = new StorageTxtFile("Test.txt");
            ArrayList<String> readContents = storage.readStorageTxtFile();
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
     * Tests for appending content to the text file storage.
     */
    @Test
    public void appendToTxtFileStorageTest() {
        try {
            File testFile = new File("Test.txt");
            ArrayList<String> testInputs = new ArrayList<>(Arrays.asList("ABC", "123"));
            StorageTxtFile storage = new StorageTxtFile("Test.txt");
            for (String testInput: testInputs) {
                storage.appendToStorageTxtFile(testInput);
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
     * Tests for clearing content from the text file storage.
     */
    @Test
    public void clearTxtFileStorage() {
        try {
            File testFile = new File("Test.txt");
            StorageTxtFile storage = new StorageTxtFile("Test.txt");
            FileWriter fw = new FileWriter("Test.txt", true);
            ArrayList<String> testInputs = new ArrayList<>(Arrays.asList("ABC", "123"));

            for (String testStr: testInputs) {
                fw.write(testStr + "\n");
            }
            fw.close();
            storage.clearStorageTxtFile();
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

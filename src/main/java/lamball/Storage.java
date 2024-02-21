package lamball;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import lamball.exception.LamballParseException;
import lamball.exception.StorageException;
import lamball.memo.Memo;

/**
 * Contains methods that pertain to file access and modification.
 *
 * @author ongzhili
 */
public class Storage {

    protected static String filePath = "src/main/java/data";
    protected static String defaultFileName = "/list.txt";
    protected static String defaultMemoName = "/memo.txt";
    protected static String defaultTempFile = "/temp.txt";

    /**
     * Obtains and initializes list from saved text file locally.
     *
     * @param lamb Initialized Lamball chatbot instance.
     * @return Text for the GUI to display.
     */

    public static String obtainSavedFile(Lamball lamb) {
        String returnVal = "";
        File folder = new File(filePath);
        if (!folder.exists()) {
            System.out.println("Folder does not exist. Creating folder...");
            returnVal += "Folder does not exist. Creating folder...\n";
            folder.mkdirs();
        }
        File savedMemo = new File(filePath + defaultMemoName);
        File savedList = new File(filePath + defaultFileName);

        try {
            returnVal += createFile(savedList, "list");
            returnVal += createFile(savedMemo, "memo");

            initializeListFromText(savedList, lamb);
            initializeMemo(savedMemo, lamb);
        } catch (IOException e) {
            String errorMessage = "An error occurred while creating the file: " + e.getMessage();
            System.err.println(errorMessage);
            returnVal += errorMessage;
            return returnVal;
        }

        assert returnVal != "" : "return value should not be empty";
        return returnVal;
    }

    private static String createFile(File fle, String name) throws IOException {
        String returnVal = "";

        if (fle.createNewFile()) {
            String message = name + "created successfully at: " + fle.getAbsolutePath();
            System.out.println(message);
            returnVal += message + "\n";
        } else {
            assert fle.exists() : "There should be a " + name + ".txt";
            String message = "Seems like you haaave a saved" + name + ", baa!";
            System.out.println(message);
            returnVal += message + "\n\n";
        }

        return returnVal;
    }

    private static void initializeMemo(File savedMemo, Lamball lamb) throws FileNotFoundException {

        assert savedMemo.exists() : "Memo file should exist.";
        System.out.println(savedMemo.exists());

        Scanner scannr = new Scanner(savedMemo);
        System.out.println("Initializing memo...");

        while (scannr.hasNext()) {
            try {
                String currLine = scannr.nextLine();
                Memo currMemo = Parser.parseMemo(currLine);
                lamb.addToMemList(currMemo, true);
            } catch (LamballParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void flush(File toFlush) throws IOException {
        toFlush.delete();
        toFlush.createNewFile();
    }


    private static void initializeListFromText(File savedList, Lamball lamb) throws FileNotFoundException {
        File tempFile = new File(filePath + defaultTempFile);
        assert (!Objects.isNull(tempFile)) : "there should be a file created";

        try {
            flush(tempFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(savedList);
        System.out.println("Initializing saved file...");
        int count = 0;
        while (scanner.hasNext()) {
            try {
                // Parses every line of the saved file - if error, deletes line in the file and ignores command
                String currLine = scanner.nextLine();
                String[] parts = Parser.parseSavedList(currLine);

                lamb.initParse(parts[1]);
                count++;
                // Marks task if first character is 1. Else does not.
                if (Integer.valueOf(parts[0]) == 1) {
                    assert count > 0 : "count should be a non-negative, non-zero value";
                    lamb.initParse("mark " + count);
                }
                // If code reaches here, means that the line is valid - write to temp file
                Storage.writeToFile(defaultTempFile, currLine);


            } catch (LamballParseException e) {
                // Does not write if the command is invalid.
                System.out.println("Corrupt format, ignoring...");
            }
        }
        scanner.close();
        System.out.println("Done!");
        savedList.delete();
        tempFile.renameTo(savedList);
    }

    /**
     * Replaces specified line in text file.
     *
     * @param toWrite Replacement line.
     * @param index Index of line to replace.
     */
    public static void replaceLine(String toWrite, int index) throws StorageException {
        try {
            // Read all lines from the file
            Path path = Paths.get(filePath + defaultFileName);
            List<String> lines = Files.readAllLines(path);

            // Check if the index is valid
            if (index >= 0 && index < lines.size()) {
                // Replace the line at the specified index
                lines.set(index, toWrite);

                // Write the modified content back to the file
                Files.write(path, lines);
            } else {
                throw new StorageException("Invalid Index!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes specified line in text file.
     *
     * @param index Index of line to delete.
     */
    public static void deleteLine(int index) {
        try {
            // Read all lines from the file
            Path path = Paths.get(filePath + defaultFileName);
            List<String> lines = Files.readAllLines(path);

            if (index >= 0 && index < lines.size()) {
                lines.remove(index);
                Files.write(path, lines);
            } else {
                System.err.println("Invalid line number.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes all lines in text file.
     *
     */
    public static void deleteAllLines(String fileName) {
        try {
            // Read all lines from the file
            File toClear = new File(filePath + fileName);
            flush(toClear);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replaces specified line in text file.
     *
     * @param fileName File name of the text file.
     * @param toAdd Line to be appended to the text file.
     */
    public static void writeToFile(String fileName, String toAdd) {
        try {
            System.out.println(new File(fileName).exists());
            FileWriter fw = new FileWriter(filePath + fileName, true);
            fw.write(toAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save Failed: " + e.getMessage());
        }
    }

    /**
     * Replaces specified line in text file. Default hardcoded path provided.
     *
     * @param toAdd Line to be appended to the text file.
     */
    public static void writeToFile(String toAdd) {
        try {
            FileWriter fw = new FileWriter(filePath + defaultFileName, true);
            fw.write(toAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save Failed: " + e.getMessage());
        }
    }

}

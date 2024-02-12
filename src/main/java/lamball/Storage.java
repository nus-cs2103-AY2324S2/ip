package lamball;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


/**
 * Contains methods that pertain to file access and modification.
 *
 * @author ongzhili
 */
public class Storage {

    protected String filePath = "src/main/java/data/list.txt";
    protected String tempFilePath = "src/main/java/data/tempfile.txt";

    /**
     * Obtains and initializes list from saved text file locally.
     *
     * @param lamb Initialized Lamball chatbot instance.
     * @return Text for the GUI to display.
     */

    public static String obtainSavedFile(Lamball lamb) {
        String returnVal = "";
        File folder = new File("src/main/java/data");
        if (!folder.exists()) {
            System.out.println("Folder does not exist. Creating folder...");
            returnVal += "Folder does not exist. Creating folder...\n";
            folder.mkdirs();
        }
        File savedList = new File("src/main/java/data/list.txt");
        try {
            // Try to create file
            if (savedList.createNewFile()) {
                System.out.println("List created successfully at: " + savedList.getAbsolutePath());
                returnVal += "List created successfully at: " + savedList.getAbsolutePath() + "\n";
            } else {
                System.out.println("Seems like I haave helped you before, so no new list is needed!");
                returnVal += "Seems like I haave helped you before, so no new list is needed!\n";
            }
            initializeListFromText(savedList, lamb);
        } catch (IOException e) {
            System.err.println("An error occurred while creating the file: " + e.getMessage());
            returnVal += "An error occurred while creating the file: " + e.getMessage();
        } finally {
            return returnVal;
        }
    }

    private static void initializeListFromText(File savedList, Lamball lamb) throws FileNotFoundException {
        File tempFile = new File("src/main/java/data/tempfile.txt");
        try {
            // Delete and creates a new tempfile
            tempFile.delete();
            tempFile.createNewFile();
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
                String[] parts = currLine.split(" \\| ", 2);
                // Means that it is not formatted correctly in the <0 or 1> | <command> format
                if (parts.length != 2 || !(Integer.valueOf(parts[0]) == 1 || Integer.valueOf(parts[0]) == 0)) {
                    throw new LamballParseException("Corrupt format, ignoring...");
                }
                lamb.initParse(parts[1]);
                count++;
                // Marks task if first character is 1. Else does not.
                if (Integer.valueOf(parts[0]) == 1) {
                    lamb.initParse("mark " + count);
                }
                // If code reaches here, means that the line is valid - write to temp file
                Storage.writeToFile("src/main/java/data/tempfile.txt", currLine);


            } catch (LamballParseException e) {
                // Ignores line
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
    public static void replaceLine(String toWrite, int index) {
        String filePath = "src/main/java/data/list.txt";
        try {
            // Read all lines from the file
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);

            // Check if the index is valid
            if (index >= 0 && index < lines.size()) {
                // Replace the line at the specified index
                lines.set(index, toWrite);

                // Write the modified content back to the file
                Files.write(path, lines);

                // System.out.println("Line " + index + " replaced successfully.");
            } else {
                // System.err.println("aaaaaaa");
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
        String filePath = "src/main/java/data/list.txt";
        try {
            // Read all lines from the file
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);

            // Check if the index is valid
            if (index >= 0 && index < lines.size()) {
                // Remove the line at the specified index
                lines.remove(index);

                // Write the modified content back to the file
                Files.write(path, lines);

                // System.out.println("Line " + index + " deleted successfully.");
            } else {
                System.err.println("Invalid line number.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replaces specified line in text file.
     *
     * @param filePath File path of the text file.
     * @param toAdd Line to be appended to the text file.
     */
    public static void writeToFile(String filePath, String toAdd) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
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
        String filePath = "src/main/java/data/list.txt";
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(toAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save Failed: " + e.getMessage());
        }
    }

}

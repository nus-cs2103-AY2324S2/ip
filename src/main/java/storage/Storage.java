package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ui.UI;

/**
 * The Storage class handles reading from and writing to a file for data persistence.
 * It provides methods for retrieving lines, writing a line, reading a line by index,
 * updating a line, and deleting a line from the stored data.
 */
public class Storage {

    /** The file path for data storage. */
    private String filePath;

    /** The File object representing the file for data storage. */
    private File file;

    /** The Path object representing the file path. */
    private Path path;

    /**
     * Constructs a Storage object with the specified file path.
     * Initializes the File and Path objects for data storage.
     * @param filePath The path to the file for data storage.
     */
    public Storage(String filePath, String fileName) {
        this.filePath = filePath + fileName;
        File directory = new File(filePath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                UI.print("Directory created: " + filePath);
            } else {
                UI.print("Failed to create directory: " + filePath);
            }
        }
        this.file = new File(this.filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    UI.print("File created: " + filePath + File.separator + fileName);
                } else {
                    UI.print("Failed to create file: " + filePath + File.separator + fileName);
                }
            } catch (IOException e) {
                UI.print("Error creating file: " + e.getMessage());
            }
        }
        this.path = Path.of(this.filePath);
    }

    /**
     * Retrieves all lines from the stored data in the file.
     * @return A List of strings representing the lines from the file.
     */
    public List<String> retrieveLines() {
        List<String> result = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            UI.print("Invalid file path provided, session will not be saved.");
        }
        return result;
    }

    /**
     * Writes a line of data to the file for data storage.
     * @param data The data to be written to the file.
     */
    public void writeLine(String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true));
            writer.write(data);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            UI.print(e.getMessage());
        }
    }

    /**
     * Reads a line from the stored data based on the specified index.
     * @param index The index of the line to be read.
     * @return The string representing the line at the specified index.
     */
    public String readLine(int index) {
        try {
            Scanner scanner = new Scanner(this.file);
            int i = 0;
            String line = "";
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (i == index) {
                    return line;
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            UI.print(e.getMessage());
        }
        return null;
    }

    /**
     * Updates a line in the stored data based on the specified index.
     * @param index The index of the line to be updated.
     * @param data The updated data to replace the existing line.
     */
    public void updateLine(int index, String data) {
        try {
            List<String> lines = Files.readAllLines(this.path);
            lines.set(index, data);
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, false));
            writer.write("");
            writer = new BufferedWriter(new FileWriter(this.file, true));
            for (String str : lines) {
                writer.write(str);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            UI.print(e.getMessage());
        }
    }

    /**
     * Deletes a line from the stored data based on the specified index.
     * @param index The index of the line to be deleted.
     */
    public void deleteLine(int index) {
        try {
            List<String> lines = Files.readAllLines(this.path);
            lines.remove(index);
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, false));
            writer.write("");
            writer = new BufferedWriter(new FileWriter(this.file, true));
            for (String str : lines) {
                writer.write(str);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            UI.print(e.getMessage());
        }
    }
}

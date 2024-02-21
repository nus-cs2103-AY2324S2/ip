package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Represents a storage object that interacts with the file system.
 */
public class Storage {
    protected String path;
    protected File file;
    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
    }

    /**
     * Loads the file.
     *
     * @return The file.
     * @throws TaroException If an error occurs while creating the file.
     */
    public File load() throws TaroException {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                throw new TaroException();
            } catch (TaroException e) {
                throw new TaroException("Something went wrong in creating the file.");
            }
        } else {
            assert file.exists() : "File should exist";
            return file;
        }
    }

    /**
     * Adds a task to the file.
     *
     * @param textToAdd The task to be added.
     * @throws IOException If an I/O error occurs.
     */
    public void addToFile(String textToAdd) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter(this.path, true));
        String line = "0 " + textToAdd;
        fw.write(line);
        fw.newLine();
        fw.close();
    }

    /**
     * Removes a task from the file.
     *
     * @param lineNumberToRemove The line number of the task to be removed.
     * @throws IOException If an I/O error occurs.
     */
    public void removeFromFile(int lineNumberToRemove) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.path));
        StringBuilder content = new StringBuilder();
        String line;
        int lineCounter = 1; // Keep track of the current line number

        // Read lines and skip the line to be deleted
        while ((line = reader.readLine()) != null) {
            if (lineCounter != lineNumberToRemove) {
                content.append(line).append(System.getProperty("line.separator"));
            }
            lineCounter++;
        }

        // Close the reader
        reader.close();

        // Write the modified content back to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.path));
        writer.write(content.toString());
        writer.close();
    }

    /**
     * Edits a line in the file.
     *
     * @param lineToEdit The line number of the task to be edited.
     * @param isDone The status of the task.
     * @throws IOException If an I/O error occurs.
     */
    public void editLineInFile(int lineToEdit, int isDone) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.path));
        StringBuilder content = new StringBuilder();
        String line;
        int lineCounter = 1;

        // Read lines and modify the specified line
        while ((line = reader.readLine()) != null) {
            if (lineCounter == lineToEdit) {
                // Split the line by space
                String[] parts = line.split(" ", 2); // Split only once

                // Check if the line has at least two parts
                if (parts.length >= 2) {
                    parts[0] = String.valueOf(isDone);

                    // Reconstruct the modified line
                    String modifiedLine = String.join(" ", parts);

                    // Append the modified line to the content
                    content.append(modifiedLine).append(System.getProperty("line.separator"));
                }
            } else {
                content.append(line).append(System.getProperty("line.separator"));
            }
            lineCounter++;
        }

        // Close the reader
        reader.close();

        // Write the modified content back to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.path));
        writer.write(content.toString());
        writer.close();
    }
}

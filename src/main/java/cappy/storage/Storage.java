package cappy.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.lang.AutoCloseable;

/**
 * Manages storage operations for the task management system.
 *
 * <p>The {@code Storage} class provides functionality for creating, reading, and writing
 * to a specified file path. It supports operations such as emptying the content, writing
 * a line to the end of the storage, and reading all content from the storage.
 *
 * <p><strong>File Path:</strong> The path to the file associated with the storage.
 */
public class Storage implements AutoCloseable {
    private final String path;
    private final File file;
    private BufferedWriter writer;

    /**
     * Constructs a new Storage object with the specified file path.
     * The file and its parent directories will be created if they do not exist.
     *
     * @param path The file path for the storage.
     * @throws IOException If an I/O error occurs during file or directory creation.
     */
    public Storage(String path) throws IOException {
        this.path = path;
        this.file = new File(path);
        this.file.getParentFile().mkdirs();
        this.file.createNewFile();
        this.writer = new BufferedWriter(new FileWriter(this.file, true));
    }

    /**
     * Emptys the content in the storage. The underlying file will be overwritten to
     * contain nothing.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void empty() throws IOException {
        close();
        try (FileWriter fileWriter = new FileWriter(this.file, false)) {
            fileWriter.write("");
        }
        this.writer = new BufferedWriter(new FileWriter(this.file));
    }

    /**
     * Writes a line to the end of the storage.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void writeLine(String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }

    /**
     * Reads all the content from the storage (underlying file) and return it as a string.
     *
     * @return The string that contains all the content from the storage.
     * @throws IOException If an I/O error occurs.
     */
    public String readAll() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.file));
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            builder.append(line);
            builder.append("\n");
            line = reader.readLine();
        }
        reader.close();
        return builder.toString();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}

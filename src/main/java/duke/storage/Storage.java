package duke.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class for Storage system of application
 *
 * @author KohGuanZeh
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates a new Storage with the specified path.
     * Files and parent directories are created if they do not exist.
     *
     * @param directoryPath Path to directory of file.
     * @param fileName Name and extension of file.
     * @throws IOException Exception when there is error creating directory or file.
     */
    public Storage(String directoryPath, String fileName) throws IOException {
        this.filePath = Paths.get(directoryPath, fileName);
        if (Files.notExists(this.filePath)) {
            Files.createDirectories(Paths.get(directoryPath));
            Files.createFile(this.filePath);
        }
        assert(Files.exists(this.filePath));
    }

    /**
     * Writes data to the file stored in filePath.
     *
     * @param data Input data to write to file.
     * @throws IOException Exception when there is an error writing to file.
     */
    public void save(String data) throws IOException {
        Files.writeString(this.filePath, data, StandardCharsets.UTF_8);
    }

    /**
     * Loads data from file into String for application to process.
     *
     * @return String or data stored in file.
     * @throws IOException Exception when there is an error opening or reading the file.
     */
    public String load() throws IOException {
        return Files.readString(this.filePath);
    }
}

package raphael.storage;

import raphael.exception.RaphaelException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * Handles task file I/O.
 */
public class Storage {
    private final Path filePath;
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        try {
            Files.createDirectories(this.filePath.getParent());
            Files.createFile(this.filePath);
        } catch (IOException e) {
//            throw new DukeException(DukeException.CONNECT_FILE_EXCEPTION);
        }
    }

    /**
     * Returns the content read from the task file.
     *
     * @return the content of the task file.
     * @throws RaphaelException the exception exclusive for Raphael.
     */
    public String load() throws RaphaelException {
        try {
            BufferedReader br = Files.newBufferedReader(this.filePath);
            String line;
            StringBuilder tasks = new StringBuilder();
            while((line = br.readLine()) != null && !line.equals("\n")) {
                tasks.append(line).append("\n");
            }
            br.close();
            return tasks.toString();
        } catch (IOException e) {
            throw new RaphaelException(RaphaelException.TYPE.READ_IO_EXCEPTION);
        }
    }

    /**
     * Writes the text accepted via argument to the task file.
     *
     * @param content the text to write to the task file.
     * @throws RaphaelException the exception exclusive for Raphael.
     */
    public void write(String content) throws RaphaelException {
        try {
            BufferedWriter bw = Files.newBufferedWriter(this.filePath);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            throw new RaphaelException(RaphaelException.TYPE.WRITE_IO_EXCEPTION);
        }
    }
}
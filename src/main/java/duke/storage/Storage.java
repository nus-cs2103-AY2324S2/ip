package duke.storage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import duke.exception.DukeException;
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
    public String load() throws DukeException {
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
            throw new DukeException(DukeException.READ_IO_EXCEPTION);
        }
    }

    public void write(String content) throws DukeException {
        try {
            BufferedWriter bw = Files.newBufferedWriter(this.filePath);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            throw new DukeException(DukeException.WRITE_IO_EXCEPTION);
        }
    }
}
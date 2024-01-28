import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private Path filePath;
    public Storage(String directoryPath, String fileName) throws IOException {
        this.filePath = Paths.get(directoryPath, fileName);
        if (Files.notExists(this.filePath)) {
            Files.createDirectories(Paths.get(directoryPath));
            Files.createFile(this.filePath);
        }
    }

    public void save(String data) throws IOException {
        Files.writeString(this.filePath, data, StandardCharsets.UTF_8);
    }

    public String load() throws IOException {
        return Files.readString(this.filePath);
    }
}

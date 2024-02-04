import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    public static final Path path = Paths.get("data", "duke.txt");

    public static List<String> loadData() throws IOException {
        Path dirPath = path.getParent();
        Files.createDirectories(dirPath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        List<String> lines = Files.readAllLines(path);
        return lines;
    }

    public void saveData (List<Task> todolist) throws IOException {
        String entry;
        Path dirPath = java.nio.file.Paths.get("data");
        Path fullPath = dirPath.resolve("duke.txt");
        Files.createDirectories(dirPath);

        try (FileWriter fw = new FileWriter(fullPath.toString(), false)) {
            for (Task t : todolist) {
                entry = t.getDataString() + "\n";
                fw.write(entry);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            throw e;
        }
    }
}

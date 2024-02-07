package chipchat.storage;

import chipchat.parser.Parser;
import chipchat.task.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {
    private static final String STORAGE_PATH = "/src/main/data/storage.txt";
    private final Path filePath;

    public Storage() {
        this.filePath = createFilePath(System.getProperty("user.dir"), STORAGE_PATH);
    }

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public static Path createFilePath(String pathToProjectRoot, String filePathFromProjectRoot) {
        String absPath = pathToProjectRoot + filePathFromProjectRoot;
        String[] paths = absPath.split("/");
        return Paths.get(paths[0], Arrays.copyOfRange(paths, 1, paths.length));
    }

    public void save(TaskList tasks) {
        try {
            if (Files.notExists(this.filePath)) {
                Files.createFile(this.filePath);
            }
        } catch (IOException x) {
            System.err.format("File existence check, IOException: %s\n", x);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            writer.write(tasks.dataString());
        } catch (IOException x) {
            System.err.format("File write, IOException: %s\n", x);
        }
    }

    public ArrayList<Task> load() {
        if (Files.notExists(this.filePath)) {
            return new ArrayList<>();
        }

        try (BufferedReader reader = Files.newBufferedReader(this.filePath, StandardCharsets.UTF_8)) {
            ArrayList<Task> tasks = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseLoadedTask(line);
                tasks.add(task);
            }
            return tasks;
        } catch (IOException x) {
            System.err.format("IOException: %s\n", x);
            return null;
        }
    }
}

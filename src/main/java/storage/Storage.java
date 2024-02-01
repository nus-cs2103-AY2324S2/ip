package storage;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String[] load() throws IOException {
        Path path = Paths.get(this.filePath);
        return Files.readString(path).split("\n");
    }

    public boolean save(String saveString) {
        try {
            // if the data directory does not exist, create it
            Files.createDirectory(Paths.get("./data"));
        } catch (IOException e) {
            // do nothing
        }

        // if the file exists, delete the old file
        Path path = Paths.get(this.filePath);
        try {
            if (Files.exists(path)) {
                Files.delete(path);
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(saveString);
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

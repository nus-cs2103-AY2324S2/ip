package chatbot;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        createFileIfNeeded();
    }

    public void createFileIfNeeded() {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rewriteFile(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Task task : tasks) {
                bw.write(task.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


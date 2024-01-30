package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import tasks.Task;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> read() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseTaskFromLine(line);
                taskList.add(task);
            }
            reader.close();
        } catch (IOException e) {
            // ignored as taskList will be returned empty
        }
        return taskList;
    }

    public void write(ArrayList<Task> taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList) {
                writer.write(task.toFile());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            // ignored, write to file failed possibly due to insufficient permissions
        }
    }
}

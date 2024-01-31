package atlas;

import atlas.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Storage {
    private String filePath;

    private TaskList tasks;

    public Storage(TaskList tasks, String filePath) {
        this.tasks = tasks;
        this.filePath = filePath;
    }

    public void load() {
        File file = new File(filePath);
        ensureFileExists();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = Parser.parseLineToTask(line);
                if (task != null) {
                    tasks.addTask(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to load tasks: " + e.getMessage());
        }


    }

    public void save(TaskList tasks) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                writer.println(task.toFileFormat());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private void ensureFileExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                File directory = file.getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }
}

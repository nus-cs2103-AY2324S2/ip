import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private static TaskList tasks;
    private static String filePath;

    public Storage(String filePath, TaskList tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
        loadFile();
    }

    public static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadFile() {
        tasks = new TaskList();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks.getTasks();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(new Task(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks.getTasks();
    }

    //    public static void createFolder() {
//        Path folder = Paths.get("./data/");
//        if (Files.notExists(folder)) {
//            try {
//                Files.createDirectories(folder);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}

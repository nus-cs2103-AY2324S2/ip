import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private static final String FILEPATH = "./data/duke.txt";

    public static void createFolder() {
        Path folder = Paths.get("./data/");
        if (Files.notExists(folder)) {
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFile(ArrayList<Task> tasks) {
        File file = new File(FILEPATH);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(new Task(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

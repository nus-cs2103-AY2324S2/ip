import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private String path;
    public FileManager(String path) {
        this.path = path;
    }

    public ArrayList<Task> loadFile() {
        List<String> tasks = new ArrayList<>();
        File file = new File(this.path);

        if (!file.exists()){
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("New task file created at: " + this.path);
            } catch (IOException e) {
                System.out.println("Failed to create save file");
            }
        } else {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String currLine = scanner.nextLine();
                    tasks.add(currLine);
                }
                scanner.close();

            } catch (IOException e) {
                System.out.println("Error loading save file");
            }
        }

        return TaskConverter.loadConvert(tasks);
    }

    public void saveFile(ArrayList<Task> taskList) {
        List<String> tasks = TaskConverter.saveConvert(taskList);
        try {
            File file = new File(this.path);
            FileWriter writer = new FileWriter(file);
            for (String task : tasks) {
                writer.write(task + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong :(");
        }
    }

}

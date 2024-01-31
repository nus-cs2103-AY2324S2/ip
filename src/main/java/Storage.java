import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected Path filePath;
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }
    private void prepareFile() {
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks(){
        prepareFile();
        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner myScannerObj = new Scanner(filePath)) {
            while (myScannerObj.hasNext()) {
                String line = myScannerObj.nextLine();
                try {
                    Task t = Task.load(line);
                    tasks.add(t);
                } catch (DukeException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                    System.out.println("Fix this line in the task file: " + line);
                    System.exit(1);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        prepareFile();
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (Task t : tasks) {
                lines.add(t.toFileString());
            }
            Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
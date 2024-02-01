import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //Load data from hard disk when BotChat starts up
    public TaskList load() {
        TaskList tasks = new TaskList();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    Task task = Parser.convertTask(line);
                    tasks.addTask(task);
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    //Save the changes to the hard disk
    public static void saveTaskToHardDisk(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks) {
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

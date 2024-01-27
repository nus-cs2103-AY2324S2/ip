import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
public class TaskManager {

    public static void saveTasksToFile(ArrayList<Task> tasks, String filePath) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter("./data/data.txt"), true)) {
            for (Task task : tasks) {
                pw.println(task.toSaveString());
            }
            System.out.println("Your session has been saved.");
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            if (file.createNewFile()) {
                saveTasksToFile(tasks, filePath);
                System.out.println("Your session has been saved.");
            } else {
                throw new IOException("Unable to create the file: " + filePath);
            }
        }
    }

    public static ArrayList<Task> loadTasksFromFile(String filepath) throws IOException, GeneralException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filepath);
        if (f.exists()) {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromSaveString(line);
                tasks.add(task);
            }
            System.out.println("Your previous session has been restored. Enjoy your chat.");
        }
        return tasks;
    }

    private static Task parseTaskFromSaveString(String saveString) throws GeneralException {
        String[] parsedData = saveString.split(",");
        String type = parsedData[0];
        Task task = null;
        switch (type) {
        case ("T"):
            task = new ToDo(parsedData[2]);
            if (parsedData[1].equals("1")) task.markAsDone();
            break;
        case ("E"):
            task = new Event(parsedData[2], parsedData[3], parsedData[4]);
            if (parsedData[1].equals("1")) task.markAsDone();
            break;
        case ("D"):
            task = new Deadline(parsedData[2], parsedData[3]);
            if (parsedData[1].equals("1")) task.markAsDone();
            break;
        default:
            break;
        }
        return task;
    }
}

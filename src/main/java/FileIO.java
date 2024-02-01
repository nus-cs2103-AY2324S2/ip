import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileIO {
    public static void writeToFile(List<? extends Task> tasks) {
        try {
            FileWriter writer = new FileWriter("scrolls_of_tasks.txt");
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

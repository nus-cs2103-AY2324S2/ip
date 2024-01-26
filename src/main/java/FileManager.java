import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class FileManager {

    // Load data on startup
    // If data file not found, create one
    public static void fetchTasks() throws IOException {
        File f = new File("data/taskData.txt");
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException eFNF) {
            f.createNewFile();
            System.out.println("File not found, I created one for you!");
        }
    }


}

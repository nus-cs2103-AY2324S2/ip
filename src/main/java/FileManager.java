import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileManager {

    // Load data on startup
    // If data file not found, create one
    public static void fetchTasks() {
        File f = new File("data/taskData.txt");
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException eFNF) {
            try {
                boolean isCreated = f.createNewFile();
                if (isCreated) {
                    System.out.println("File not found, I created one for you!");
                }
            } catch (IOException eIO) {
                System.out.println("IO error occurred when creating file.");
            }

        }
    }

    // Rewrite file using data in Storage
    public static void saveTasks() {
        try (FileWriter fw = new FileWriter("data/taskData.txt")){
            for (Task t : Storage.getTasks()) {
                fw.write(t.toString() + "\n");
            }
//            System.out.println("Update success");
        } catch (IOException eIO) {
            System.out.println("IO error occurred while updating.");
        }
    }
}

package ukecat;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileManager {

    public static void loadTasks() {
        File f = new File("data/taskData.txt");
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                Storage.loadTask(s.nextLine());
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

    public static void updateTasks() {
        try (FileWriter fw = new FileWriter("data/taskData.txt")){
            for (Task t : Storage.getTasks()) {
                fw.write(Parser.parseTaskToCsv(t) + "\n");
            }
        } catch (IOException eIO) {
            System.out.println("IO error occurred while updating.");
        }
    }
}

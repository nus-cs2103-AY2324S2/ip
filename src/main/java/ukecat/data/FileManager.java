package ukecat.data;

import ukecat.command.Parser;
import ukecat.task.Task;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileManager {
    // Load data on startup
    // If data file not found, create one
    // Reads data line by line
    // For each line, ask ukecat.data.Storage to loadTask()
    // ukecat.data.Storage asks ukecat.command.Parser to parse line
    // Task can be created to be added (with mark info too)
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

    // Rewrite file using data in ukecat.data.Storage
    public static void updateTasks() {
        try (FileWriter fw = new FileWriter("data/taskData.txt")){
            for (Task t : Storage.getTasks()) {
                fw.write(Parser.parseTaskToCsv(t) + "\n");
            }
//            System.out.println("Update success");
        } catch (IOException eIO) {
            System.out.println("IO error occurred while updating.");
        }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private Parser parser;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.parser = new Parser();
    }

    //Load data from file --> Change to just return task list itself...
    public ArrayList<Task> loadData() throws DuchessException {
        TaskList taskList = new TaskList();
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.createNewFile(); // Create file if it doesn't exist
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = this.parser.parseTaskFromFileString(line); // Parse task from file line
                if (task != null) {
                    taskList.getTasks().add(task); // Add task to the list
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return taskList.getTasks();
    }

    //Saves the data to the file after every change, rewriting each task in the list
    public void saveData(TaskList taskList) {
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileString() + "\n"); // Write each task to file
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

}

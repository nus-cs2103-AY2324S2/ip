import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void checkFile() throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            if (f.createNewFile()) {
                System.out.println("Data file created.");
            } else {
                System.out.println("Error creating data file.");
            }
        } else {
            System.out.println("Loading data from Duke.txt...");
        }
    }

    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < tasks.length(); i++) {
            fw.write(tasks.getTask(i).toString() + System.lineSeparator());
        }
        fw.close();
    }

    public TaskList loadDataFromFile() throws IOException {
        File file = new File(filePath);
        TaskList tasks = new TaskList();

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                if (!taskData.isEmpty()) {
                    Task task = Task.parseTask(taskData);
                    if (task != null) {
                        tasks.addTaskFromData(task);
                    }
                }
            }
        }
        if (tasks.isEmpty()) {
            tasks = new TaskList();
        }
        return tasks;
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
}

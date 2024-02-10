import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.util.Arrays;

public class TaskList extends ArrayList<Task> {
    private static TaskList instance;
    private final String filePath;

    public TaskList(String filePath) {
        this.filePath = filePath;
        loadTasks();
    }
    public static TaskList getInstance(String filePath) {
        if (instance == null) {
            instance = new TaskList(filePath);
        }
        return instance;
    }

    public void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : this) {
                writer.println(task.toString());
            }
        } catch (IOException e) {
            System.out.println("Error: Tasks.txt file not found / may be corrupted.");
        }
    }

    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] textSections = line.split("\\|");
                    CommandScanner.stringToTaskParser(textSections, this);
                }
            }
        } catch (IOException e) {
            try {
                File file = new File(filePath);
                File directory = file.getParentFile();
                if (!directory.exists()) {
                    if (directory.mkdirs()) {
                        System.out.println("Directory created: " + directory.getAbsolutePath());
                    } else {
                        throw new IOException();
                    }
                }
                if (!file.exists()) {
                    if (file.createNewFile()) {
                        System.out.println("File created: " + filePath);
                    } else {
                        throw new IOException();
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error creating file: " + ex.getMessage());
            }
        } catch (DukeException e) {
            throw new RuntimeException("Error parsing tasks: " + e.getMessage(), e);
        }
    }

    public void addTask(Task task) {
        add(task);
        System.out.println("_________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task.toString());
        String numOfTask;
        if (this.size() < 2) {
            numOfTask = this.size() + " task";
        } else {
            numOfTask = this.size() + " tasks";
        }
        System.out.println("Now you have " + numOfTask + " in the list.");
        System.out.println("_________________________________________");
    }

    public Task getTask(int index) throws IndexOutOfBoundsException, IllegalArgumentException{
        return get(index);
    }
    public void displayTasks() {
        if (isEmpty()) {
            System.out.println("_________________________________________");
            System.out.println("No pending tasks, congrats!");
            System.out.println("_________________________________________");
        } else {
            System.out.println("_________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < size(); i++) {
                System.out.println((i + 1) + ". " + getTask(i));
            }
            System.out.println("_________________________________________");
        }
    }
    public void deleteTask(int index) {
        Task task = this.get(index);
        System.out.println("_________________________________________");
        System.out.println("  OK! I've removed this task:");
        System.out.println("    " + task);
        this.remove(index);
        String numOfTask;
        if (this.size() < 2) {
            numOfTask = this.size() + " task";
        } else {
            numOfTask = this.size() + " tasks";
        }
        System.out.println("  Now you have " + numOfTask + " in the list.");
        System.out.println("_________________________________________");
    }

}

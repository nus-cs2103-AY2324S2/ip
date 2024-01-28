import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class Storage {
    private static String PATH_TO_TASKS_FILE = "echo_bot_tasks.txt";

    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        saveToFile();
    }

    public void deleteTask(int index) {
        tasks.remove(index);
        saveToFile();
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void printTaskCount() {
        int count = getTaskCount();
        System.out.println("Now you have " + count + " " + (count == 1 ? "task" : "tasks") + " in the list.");
    }

    public String getTaskDescription(int index) {
        return tasks.get(index).toString();
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
        saveToFile();
    }

    public void unmarkTaskAsDone(int index) {
        tasks.get(index).unmarkAsDone();
        saveToFile();
    }

    public void loadFromFile(Parser parser) {
        tasks.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_TASKS_FILE))) {
            String command = reader.readLine();
            while (command != null) {
                parser.parse(command).executeSilently(this);
                command = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            // Do nothing
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (DukeException e) {
            System.err.println("Stored tasks file is corrupted: " + e.getMessage());
            tasks.clear();
            // Delete the corrupted file
            new File(PATH_TO_TASKS_FILE).delete();
            System.err.println("Deleted the file.");
        }
    }

    /**
     * Saves the current list of tasks to the file.
     */
    private void saveToFile() {
        // We write to a temporary file first, then rename it to the actual file.
        // This is to prevent the file from being corrupted if the program crashes.
        final String PATH_TO_TMP_FILE = PATH_TO_TASKS_FILE + ".tmp";
        try (FileWriter writer = new FileWriter(PATH_TO_TMP_FILE)) {
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).serializeToCommand(i));
            }
            new File(PATH_TO_TMP_FILE).renameTo(new File(PATH_TO_TASKS_FILE));
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
};

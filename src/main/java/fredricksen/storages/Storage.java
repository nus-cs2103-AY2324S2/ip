package fredricksen.storages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import fredricksen.tasks.Deadline;
import fredricksen.tasks.Event;
import fredricksen.tasks.Task;
import fredricksen.tasks.TaskList;
import fredricksen.tasks.ToDo;

/**
 * Represents a Storage class that have operations that can
 * create, read, and write to a file that stores a list of Task type tasks
 * in a nicely formatted manner in a String format.
 */
public class Storage {
    private File file;
    private Path path;

    /**
     * Constructs a Storage instance with a specified file path.
     *
     * @param file A file path destination to store the String format Task.
     */
    public Storage(String file) {
        this.file = new File(file);
        this.path = Paths.get(this.file.toURI());
    }

    /**
     * Creates a new Task based on the taskType passed into the function.
     *
     * @param taskType A String that represents the type of task.
     * @param content A String that represents the task description.
     * @param isDone A boolean that represents if the task have been mark done.
     * @return A Task that have been created based on the task type.
     */
    public Task createNewTask(String taskType, String content, boolean isDone) {
        switch (taskType) {
        case "D": {
            Deadline newTask = new Deadline(content, taskType, isDone);
            return newTask;
        }
        case "T": {
            ToDo newTask = new ToDo(content, taskType, isDone);
            return newTask;
        }
        case "E": {
            Event newTask = new Event(content, taskType, isDone);
            return newTask;
        }
        default:
            return new ToDo(content, taskType, isDone);
        }
    }

    /**
     * Returns an ArrayList of Task based on the data
     * stored in the Fredricksen.txt file.
     *
     * @return a TaskList of Task
     * @throws IOException if file cannot be opened or read.
     */
    public TaskList loadFile() throws IOException {
        TaskList list = new TaskList();

        try (BufferedReader br = new BufferedReader(new FileReader(this.path.toFile()))) {
            String next;
            while ((next = br.readLine()) != null) {
                int type = next.indexOf("type: ");
                int doneIndex = next.indexOf("isDone: ");
                int content = next.indexOf("content: ");
                boolean isDone = next.substring(doneIndex + 8, content - 1).equals("true");
                Task newTask = createNewTask(next.substring(type + 6, type + 7), next.substring(content + 9), isDone);
                list.addTask(newTask);
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        return list;
    }

    /**
     * Updates the file specified by this object's path with the elements of TaskList.
     * Each task in the list is written to the file in a specific format.
     * Each task's details are on a separate line.
     *
     * @param list The TaskList containing tasks to be written to the file. Each task in the list
     *             is expected to have a type, a completion status, and content.
     */
    public void updateFile(TaskList list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.path.toFile()))) {
            for (int i = 0; i < list.size(); i++) {
                Task task = list.getTask(i);
                String type = task.getType();
                String content = task.getTask();
                boolean isDone = task.getDone();
                bw.write("type: " + type + " isDone: " + isDone + " content: " + content);
                bw.newLine();
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Checks if the directory with the object's file path to store the txt file exists,
     * if it doesn't, it creates the directory. Then check if the file exist, if it doesn't,
     * then creates the file under the directory.
     */
    public void createFileInData() {
        Path dataDir = this.path.getParent();

        if (!Files.exists(dataDir)) {
            try {
                Files.createDirectories(dataDir);
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
        }

        if (!Files.exists(this.path)) {
            try {
                Files.createFile(this.path);
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
        }
    }
}

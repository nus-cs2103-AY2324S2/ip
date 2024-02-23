package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import exceptions.WeiException;
import taskList.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Stores the task added by the user.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Creates a storage object.
     *
     * @param filePath Leads to the text file that stores the saved tasks.
     * @throws IOException If text file cannot be created.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!file.exists()) {
            Files.createDirectories(file.toPath().getParent());
            file.createNewFile();
        }
    }

    /**
     * Loads the tasks from the text file into the TaskList object.
     *
     * @return A TaskList containing the tasks.
     * @throws FileNotFoundException If file does not exist.
     */
    public TaskList read() throws FileNotFoundException, WeiException {
        TaskList tasks = new TaskList();
        Scanner s = new Scanner(file); // create a Scanner using the File as the source

        while (s.hasNext()) {
            Task curr;
            String temp = s.nextLine();
            String[] split = temp.split(" \\| ");

            String type = split[0];
            boolean isComplete = split[1].equals("X");
            String description = split[2];
            String reminderDate = split[3];

            switch (type) {
            case "T":
                curr = new ToDo(description, reminderDate, isComplete);
                break;
            case "D":
                String deadline = split[4];
                curr = new Deadline(description, reminderDate, deadline, isComplete);
                break;
            case "E":
                String startDate = split[4];
                String endDate = split[5];
                curr = new Event(description, reminderDate, startDate, endDate, isComplete);
                break;
            default:
                throw new WeiException("file corrupted");
            }
            tasks.add(curr);
        }

        return tasks;
    }

    /**
     * Saves the tasks in the TaskList object into the text file.
     *
     * @param tasks TaskList containing the tasks.
     * @throws IOException If file does not exist.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            fileWriter.write(task.toString() + "\n");
        }
        fileWriter.close();
    }
}

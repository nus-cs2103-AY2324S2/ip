package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
            file.createNewFile();
        }
    }

    /**
     * Loads the tasks from the text file into the TaskList object.
     *
     * @return A TaskList containing the tasks.
     * @throws FileNotFoundException If file does not exist.
     */
    public TaskList read() throws FileNotFoundException {
        TaskList tasks = new TaskList();
        Scanner s = new Scanner(file); // create a Scanner using the File as the source

        while (s.hasNext()) {
            Task curr;
            String temp = s.nextLine();
            String[] split = temp.split(" \\| ");
            boolean isComplete = split[1].equals("X");
            if (split[0].equals("T")) {
                curr = new ToDo(split[2], isComplete);
            } else if (split[0].equals("D")) {
                curr = new Deadline(split[2], split[3], isComplete);
            } else {
                curr = new Event(split[2], split[3], split[4], isComplete);
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
            Task temp = tasks.getTask(i);
            fileWriter.write(temp.toString() + "\n");
        }
        fileWriter.close();
    }
}

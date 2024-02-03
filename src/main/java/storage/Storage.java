package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

/**
 * The Storage class can modify and store the task list.
 * The task list is stored locally in a .txt file.
 *
 * @author Maximilliano Utomo
 */
public class Storage {
    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * A public constructor for the Storage class.
     */
    public Storage() {
        tasks = new ArrayList<>(100);

        try {
            File dataFolder = new File("./data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File dataFile = new File("./data/data.txt");
            if (!dataFile.exists()) {
                dataFolder.createNewFile();
            } else {
                this.readTasksFromData();
            }
        } catch (IOException error) {
            System.out.println("Sorry, Sensei! I seem to be struggling to load the tasks :(");
        }
    }

    /**
     * Fetches the locally saved data containing the task list.
     * @throws IOException - an exception thrown if the data is not read
     */
    private void readTasksFromData() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File dataFile = new File("./data/data.txt");
        Scanner istream = new Scanner(dataFile);

        while (istream.hasNextLine()) {
            String[] streamSplit = istream.nextLine().split(Pattern.quote("|"), 2);
            String taskType = streamSplit[0];
            String[] taskArguments = streamSplit[1].split(Pattern.quote("|"), 0);

            Task task;
            if (taskType.equals("T")) {
                task = new ToDo(taskArguments[1]);
            } else if (taskType.equals("D")) {
                task = new Deadline(taskArguments[1], taskArguments[2]);
            } else { // if (taskType.equals("E"))
                task = new Event(taskArguments[1], taskArguments[2], taskArguments[3]);
            }

            if (taskArguments[0].equals("1")) {
                task.setDone();
            } else {
                task.setNotDone();
            }

            tasks.add(task);
        }

        this.tasks = tasks;
        istream.close();
    }

    /**
     * Saves the task list to a local save data.
     */
    private void writeTasks() {
        try {
            FileWriter ostream = new FileWriter("./data/data.txt");
            for (Task task : this.tasks) {
                ostream.write(task.toDataFormat() + "\n");
            }
            ostream.close();
        } catch (IOException error) {
            System.out.println("Sorry, Sensei! I seem to be struggling to save the tasks :(");
        }
    }

    /**
     * Checks whether the list is empty or not.
     * @return True if the list is empty, False otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Checks whether the given index is available in the task list.
     * @param id - the index to be checked
     * @return True if the index is inside bounds, False otherwise
     */
    public boolean checkIndexValidity(int id) {
        id = id - 1;
        return 0 <= id && id < tasks.size();
    }

    /**
     * Retrieves the number of tasks in the list.
     * @return The number of tasks.
     */
    public int taskCount() {
        return tasks.size();
    }

    /**
     * Adds a task to the back of the list.
     * @param task - the Task that is added to the list
     */
    public void addElements(Task task) {
        tasks.add(task);
        this.writeTasks();
    }

    /**
     * Removes the task of a given index from the list.
     * @param id - the index of the task to be deleted
     */
    public void deleteElements(int id) {
        tasks.remove(id);
        this.writeTasks();
    }

    /**
     * Prints the list.
     */
    public void printElements() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Marks the task of given index from the list as done.
     * @param id - the index of the task to be marked
     */
    public void markIndexAsDone(int id) {
        Task task = tasks.get(id);
        task.setDone();
        this.writeTasks();
    }

    /**
     * Marks the task of given index from the list as not done.
     * @param id - the index of the task to be unmarked
     */
    public void markIndexAsUndone(int id) {
        Task task = tasks.get(id);
        task.setNotDone();
        this.writeTasks();
    }

    /**
     * Retrieves the task of given index from the list.
     * @param id - the index
     * @return The task at index id
     */
    public Task getTask(int id) {
        return tasks.get(id);
    }
}

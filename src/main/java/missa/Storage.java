package missa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import missa.exception.WrongTaskDataException;
import missa.task.Deadline;
import missa.task.DoAfter;
import missa.task.Event;
import missa.task.Task;
import missa.task.ToDo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from data file.
     *
     * @return A taskList stored in data file.
     */
    public ArrayList<Task> loadData()
            throws FileNotFoundException, WrongTaskDataException {
        ArrayList<Task> tasks = new ArrayList<>(100);

        // Scans stored data.
        File data = new File(this.filePath);
        Scanner s = new Scanner(data);
        while (s.hasNext()) {
            tasks.add(getTasksFromData(s.nextLine()));
        }
        s.close();
        return tasks;
    }

    /**
     * Fetches tasks stored in hard disk.
     *
     * @param task String representation of task stored.
     * @return A missa.task.Task in data file.
     * @throws WrongTaskDataException Alerts users when wrong data detected.
     */
    public Task getTasksFromData(String task) throws WrongTaskDataException {
        String[] taskContent = task.split(" \\| ", 5);
        switch (taskContent[0]) {
        case "T":
            return getTodoTask(taskContent);
        case "D":
            return getDeadlineTask(taskContent);
        case "E":
            return getEventTask(taskContent);
        case "DA":
            return getDoAfterTask(taskContent);
        default:
            throw new WrongTaskDataException();
        }
    }

    /**
     * Returns a do-after task
     *
     * @param taskContent Content and condition of the task.
     * @return A do-after task.
     * @throws WrongTaskDataException Alerts users that data file stores incorrect info.
     */
    private static Task getDoAfterTask(String[] taskContent)
            throws WrongTaskDataException {
        if (taskContent.length != 4) {
            throw new WrongTaskDataException();
        }
        Task t4 = new DoAfter(taskContent[2], taskContent[3]);
        if (taskContent[1].equals("1")) {
            t4.mark();
        }
        return t4;
    }

    /**
     * Returns an event task.
     *
     * @param taskContent Task content and duration.
     * @return An event task.
     * @throws WrongTaskDataException Alerts user if data file contains wrong data format.
     */
    private static Task getEventTask(String[] taskContent)
            throws WrongTaskDataException {
        if (taskContent.length != 5) {
            throw new WrongTaskDataException();
        }
        Task t3 = new Event(
                taskContent[2],
                LocalDateTime.parse(taskContent[3]),
                LocalDateTime.parse(taskContent[4]));
        if (taskContent[1].equals("1")) {
            t3.mark();
        }
        return t3;
    }

    /**
     * Returns a deadline task.
     *
     * @param taskContent Task content and deadline timing.
     * @return A deadline task.
     * @throws WrongTaskDataException Alerts user if data file stores wrong data format.
     */
    private static Task getDeadlineTask(String[] taskContent)
            throws WrongTaskDataException {
        if (taskContent.length != 4) {
            throw new WrongTaskDataException();
        }
        Task t2 = new Deadline(
                taskContent[2],
                LocalDateTime.parse(taskContent[3]));
        if (taskContent[1].equals("1")) {
            t2.mark();
        }
        return t2;
    }

    /**
     * Returns a todo task.
     *
     * @param taskContent Content of the task.
     * @return A todo task.
     * @throws WrongTaskDataException Alerts user if data file does not contain task content.
     */
    private static Task getTodoTask(String[] taskContent)
            throws WrongTaskDataException {
        if (taskContent.length != 3) {
            throw new WrongTaskDataException();
        }
        Task t1 = new ToDo(taskContent[2]);
        if (taskContent[1].equals("1")) {
            t1.mark();
        }
        return t1;
    }

    /**
     * Writes updated data back to data file.
     *
     * @param newData Updated data to be stored before program is closed.
     * @throws IOException Alerts users that data file cannot be written.
     */
    public void writeBackData(String newData) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.append(newData);
        fileWriter.close();
    }
}

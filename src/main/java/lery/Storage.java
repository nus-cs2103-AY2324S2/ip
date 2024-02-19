package lery;

import lery.task.Deadline;
import lery.task.Event;
import lery.task.Task;
import lery.task.TaskList;
import lery.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



/**
 * Represents a storage.
 * The Storage class handles the loading and saving of tasks from/to a text file for the Lery chatbot application.
 * It interacts with the {@link TaskList} class to manage the task list.
 *
 * The class reads tasks from a text file in a specific format and adds them to the task list.
 * It also saves new tasks to the text file when added.
 */
public class Storage {
    private final String filename = "./data/lery.txt";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private TaskList taskList;

    /**
     * Constructs a Storage object with an empty task list.
     */
    public Storage() {
        this.taskList = new TaskList();

    }

    /**
     * Loads tasks from the text file into the task list.
     *
     * @return the TaskList containing loaded tasks.
     * @throws LeryException if an error occurs during the loading process.
     */
    public TaskList loadTasks() throws LeryException {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                throw new LeryException("Woof! Text file storage does not exist");
            } else {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    assert line.contains("|") : "Command in text file should contain '|'";
                    String[] splitLine = line.split(" \\| ");
                    String event = splitLine[2];
                    String type = splitLine[0];
                    Task newTask;
                    if (splitLine.length < 3 || splitLine.length > 4) {
                        throw new LeryException("Woof! Textfile not in correct format!" + splitLine.length);
                    }
                    switch (type) {
                    case "T":
                        newTask = new Todo(event);
                        break;
                    case "D":
                        String extraInfo = splitLine[3];
                        checkDateFormat(extraInfo);
                        newTask = new Deadline(event, extraInfo);
                        break;
                    case "E":
                        String extra = splitLine[3];
                        newTask = new Event(event, extra);
                        break;
                    default:
                        throw new LeryException("Woof! Invalid type!" + type);
                    }
                    this.taskList.add(newTask);
                }
                s.close();
            }
            return this.taskList;
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            throw new LeryException("Woof! Error loading tasks from file");
        }
    }

    /**
     * Saves a new task to the text file and adds it to the task list.
     *
     * @param newTask the task to be saved.
     * @throws LeryException if an error occurs during the saving process.
     */
    public void saveTasks(Task newTask) throws LeryException {
        String msg = " | 0 | ";
        msg = "\n" + newTask.getType() + msg + newTask.getDescription();
        if (newTask instanceof Event || newTask instanceof Deadline) {
            msg += ("| " + newTask.getExtraInfoShortened());
        }
        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(msg);
            fw.close();
            this.taskList.add(newTask);

        } catch (IOException e) {
            throw new LeryException("Woof! Error saving tasks to file");
        }
    }

    /**
     * Checks if the given date in the command string is in the correct format (yyyy-MM-dd).
     *
     * @param date the date string to be checked.
     * @throws LeryException if the date is not in the correct format.
     */
    public void checkDateFormat(String date) throws LeryException {
        try {
            LocalDate.parse(date, this.formatter);
        } catch (Exception ex) {
            throw new LeryException("Woof! Date not keyed in correct format! Correct format is yyyy-MM-dd");
        }
    }

    /**
     * Retrieves the current task list.
     *
     * @return the TaskList containing tasks.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return the size of the task list.
     */
    public int getSize() {
        return this.taskList.getSize();
    }

    /**
     * Deletes a specified task from the task list.
     *
     * @param t the task to be deleted.
     */
    public void deleteTask(Task t) {
        this.taskList.delete(t);
    }
    public void sortTask() {
        this.taskList.sort();
    }

}

package lery;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

import lery.task.Deadline;
import lery.task.Event;
import lery.task.Task;
import lery.task.TaskList;
import lery.task.Todo;





/**
 * Represents a storage.
 * The Storage class handles the loading and saving of tasks from/to a text file for the Lery chatbot application.
 * It interacts with the {@link TaskList} class to manage the task list.
 *
 * The class reads tasks from a text file in a specific format and adds them to the task list.
 * It also saves new tasks to the text file when added.
 */
public class Storage {
    private final String filename = "data/lery.txt";

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
                try {
                    Files.createDirectories(file.toPath().getParent());
                    file.createNewFile();
                } catch (IOException e) {
                    throw new LeryException("Woof! Error creating text file.");
                }
            } else {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    assert line.contains("|") : "Command in text file should contain '|'";
                    String[] splitLine = line.split(" \\| ");
                    if (splitLine.length < 3 || splitLine.length > 4) {
                        throw new LeryException("Woof! Textfile not in correct format!" + splitLine.length);
                    }
                    Task newTask = createTaskFromSplitLine(splitLine);
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
     * Creates a Task object based on the information provided in a string array.
     * The string array represents a line from the text file, with elements separated by "|".
     *
     * @param splitLine The array containing information about the task.
     * @return A Task object.
     * @throws LeryException If there is an error creating the task, such as an invalid type.
     */
    private Task createTaskFromSplitLine(String[] splitLine) throws LeryException {
        String event = splitLine[2];
        String type = splitLine[0];
        String isDoneSign = splitLine[1];
        System.out.println(Arrays.toString(splitLine));
        boolean isDone = false;
        if (isDoneSign.contains("X")) {
            isDone = true;
        }
        switch (type) {
        case "T":
            return new Todo(event, isDone);
        case "D":
            String extraInfo = splitLine[3];
            checkDateFormat(extraInfo);
            return new Deadline(event, extraInfo, isDone);
        case "E":
            String extra = splitLine[3];
            return new Event(event, extra, isDone);
        default:
            throw new LeryException("Woof! Invalid type!" + type);
        }
    }

    /**
     * Saves a new task to the text file and adds it to the task list.
     *
     * @param tlist The TaskList to be saved.
     * @throws LeryException if an error occurs during the saving process.
     */
    public void saveTasks(TaskList tlist) throws LeryException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (int i = 0; i < tlist.getSize(); i++) {
                Task task = tlist.getTask(i);
                StringBuilder msg = new StringBuilder(task.getType() + " | "
                        + task.getStatusIcon() + " | " + task.getDescription());
                if (task instanceof Event || task instanceof Deadline) {
                    msg.append(" | ").append(task.getExtraInfoShortened());
                }
                writer.write(msg.toString());
                writer.newLine();
            }

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
     * @return the updated task list.
     */
    public TaskList deleteTask(Task t) {
        this.taskList.delete(t);
        return this.taskList;
    }

    /**
     * Sorts the task list.
     */
    public void sortTask() {
        this.taskList.sort();
    }

}

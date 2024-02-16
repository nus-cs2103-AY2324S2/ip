package harper.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import harper.exceptions.HarperFileCreatingException;
import harper.exceptions.HarperFileLoadingException;
import harper.exceptions.HarperFileStoringException;
import harper.tasks.Deadline;
import harper.tasks.Event;
import harper.tasks.Task;
import harper.tasks.ToDo;

/**
 * The Storage class handles all interactions with the hard disk, including loading from
 * and storing into hard disk.
 */
public class Storage {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy H:mm");
    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private File file;

    //CHECKSTYLE.OFF: MissingJavadocMethod
    public Storage(String folderName, String fileName) {
        File folder = new File(PROJECT_DIR, folderName);
        try {
            if (!folder.exists()) {
                folder.mkdir();
            }
            File file = new File(folder, fileName);
            this.file = file;
            if (!file.exists()) {
                file.createNewFile();
                this.file = file;
            }
        } catch (IOException | SecurityException e) {
            throw new HarperFileCreatingException();
        }
    }

    /**
     * Loads data from hard disk.
     *
     * @return An ArrayList contains the data from hard disk.
     */
    public ArrayList<Task> load() {
        assert this.file != null : "data file is not created yet";
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String[] taskString = scanner.nextLine().split(" \\| ");
                boolean isOne = taskString[1].equals("1");
                boolean isZero = taskString[1].equals("0");
                boolean isDone = checkIsDone(isZero, isOne);
                String description = taskString[2];

                if (taskString[0].equals("T")) {
                    addToDo(tasks, description, isDone);
                    assert taskString.length == 3 : "The format is wrong";
                } else if (taskString[0].equals("D")) {
                    assert taskString.length == 4 : "The format is wrong";
                    String by = taskString[3];
                    addDeadline(tasks, description, isDone, by);
                } else if (taskString[0].equals("E")) {
                    assert taskString.length == 4 : "The format is wrong";
                    String[] duration = taskString[3].split(" - ");
                    String start = duration[0];
                    String end = duration[1];
                    addEvent(tasks, description, isDone, start, end);
                } else {
                    throw new HarperFileLoadingException();
                }
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new HarperFileLoadingException();
        }
    }

    /**
     * Checks if the task is done.
     *
     * @param isZero Boolean indicates whether the value read from file is zero.
     * @param isOne Boolean indicates whether the value read from file is one.
     * @return True if isOne is true, else if isZero is true, false, else, error.
     */
    public boolean checkIsDone(boolean isZero, boolean isOne) {
        if (isZero) {
            return false;
        } else if (isOne) {
            return true;
        } else {
            throw new HarperFileLoadingException();
        }
    }

    /**
     * Adds the todo task read from hard disk into the list.
     *
     * @param tasks List to store the tasks.
     * @param description Description of the task.
     * @param isDone Indicator of whether the task is done.
     */
    public void addToDo(ArrayList<Task> tasks, String description, boolean isDone) {
        Task task = new ToDo(description, isDone);
        tasks.add(task);
    }

    /**
     * Adds the deadline task read from hard disk into the list.
     *
     * @param tasks List to store the tasks.
     * @param description Description of the task.
     * @param isDone Indicator of whether the task is done.
     * @param by Deadline of the task.
     */
    public void addDeadline(ArrayList<Task> tasks, String description, boolean isDone, String by) {
        LocalDateTime byFormatted = LocalDateTime.parse(by, DATE_TIME_FORMATTER);
        Task task = new Deadline(description, isDone, byFormatted);
        tasks.add(task);
    }

    /**
     * Adds the event task read from hard disk into the list.
     *
     * @param tasks List to store the tasks.
     * @param description Description of the task.
     * @param isDone Indicator of whether the task is done.
     * @param start Start time of the task.
     * @param end End time of the task.
     */
    public void addEvent(ArrayList<Task> tasks, String description, boolean isDone, String start, String end) {
        LocalDateTime startDateTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER);
        LocalDateTime endDateTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER);
        Task task = new Event(description, isDone, startDateTime, endDateTime);
        tasks.add(task);
    }

    /**
     * Stores the task list into hard disk.
     *
     * @param taskList task.Task list to be stored.
     */
    public void save(TaskList taskList) {
        assert taskList != null : "taskList should not be null even it contains nothing";
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(taskList.listTasksIntoFile());
            fw.close();
        } catch (IOException e) {
            throw new HarperFileStoringException();
        }
    }
}

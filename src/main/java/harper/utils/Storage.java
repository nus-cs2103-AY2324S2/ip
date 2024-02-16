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
                boolean isDone;
                if (taskString[1].equals("1")) {
                    isDone = true;
                } else if (taskString[1].equals("0")) {
                    isDone = false;
                } else {
                    throw new HarperFileLoadingException();
                }

                if (taskString[0].equals("T")) {
                    assert taskString.length == 3 : "The format is wrong";
                    Task task = new ToDo(taskString[2], isDone);
                    tasks.add(task);
                } else if (taskString[0].equals("D")) {
                    assert taskString.length == 4 : "The format is wrong";
                    String by = taskString[3];
                    LocalDateTime byFormatted = LocalDateTime.parse(by, DATE_TIME_FORMATTER);
                    Task task = new Deadline(taskString[2], isDone, byFormatted);
                    tasks.add(task);
                } else if (taskString[0].equals("E")) {
                    assert taskString.length == 4 : "The format is wrong";
                    String[] duration = taskString[3].split(" - ");
                    LocalDateTime start = LocalDateTime.parse(duration[0], DATE_TIME_FORMATTER);
                    LocalDateTime end = LocalDateTime.parse(duration[1], DATE_TIME_FORMATTER);
                    Task task = new Event(taskString[2], isDone, start, end);
                    tasks.add(task);
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

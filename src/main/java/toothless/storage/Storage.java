package toothless.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import toothless.exception.ToothlessException;
import toothless.task.Deadline;
import toothless.task.Event;
import toothless.task.Tag;
import toothless.task.Task;
import toothless.task.ToDo;

/**
 * A class to deal with loading data from and saving data to file in computer.
 */
public class Storage {
    private static final DateTimeFormatter DATETIME_PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String filePath;
    private File file;

    /**
     * A public constructor to initialize storage.
     *
     * @param filePath A String indicating the filepath where data would be stored.
     * @throws ToothlessException if file failed to be created.
     */
    public Storage(String filePath) throws ToothlessException {
        this.filePath = filePath;
        this.file = new File(filePath);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new ToothlessException(e.getMessage());
        }
    }

    /**
     * Loads data from file into ArrayList of Task objects.
     *
     * @return ArrayList of Task objects with data from file.
     * @throws ToothlessException if file is corrupted.
     */
    public ArrayList<Task> loadStorage() throws ToothlessException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner tasklistScanner = new Scanner(this.file);
            while (tasklistScanner.hasNext()) {
                String line = tasklistScanner.nextLine();
                String[] taskArgs = line.split(" \\| ");
                addTaskToTaskList(tasks, taskArgs);
            }
            tasklistScanner.close();
        } catch (IOException e) {
            throw new ToothlessException(e.getMessage());
        }
        return tasks;
    }

    /**
     * Adds task from storage to task list.
     *
     * @param tasks ArrayList of Task objects.
     * @param taskArgs String array of task arguments.
     * @throws ToothlessException if task arguments from file are corrupted.
     */
    private void addTaskToTaskList(ArrayList<Task> tasks, String[] taskArgs) throws ToothlessException {
        try {
            // get task type, done status, description
            String taskType = taskArgs[0];
            boolean isDone = taskArgs[1].equals("0") ? false : true;
            String taskDescription = taskArgs[2];
            // get tags
            ArrayList<Tag> tags = new ArrayList<>();
            if (!taskArgs[3].contains("NIL") || taskArgs[3].isBlank()) {
                String[] tagLabelsArray = taskArgs[3].split(", ");
                for (String label : tagLabelsArray) {
                    tags.add(new Tag(label));
                }
            }
            // add tasks by type
            if (taskType.equals("T")) {
                ToDo newToDo = new ToDo(taskDescription, isDone, tags);
                tasks.add(newToDo);
            } else if (taskType.equals("D")) {
                LocalDateTime deadlineBy = LocalDateTime.parse(taskArgs[4], DATETIME_PARSE_FORMATTER);
                Deadline newDeadline = new Deadline(taskDescription, isDone, tags, deadlineBy);
                tasks.add(newDeadline);
            } else if (taskType.equals("E")) {
                LocalDateTime eventFrom = LocalDateTime.parse(taskArgs[4], DATETIME_PARSE_FORMATTER);
                LocalDateTime eventTo = LocalDateTime.parse(taskArgs[5], DATETIME_PARSE_FORMATTER);
                Event newEvent = new Event(taskDescription, isDone, tags, eventFrom, eventTo);
                tasks.add(newEvent);
            } else {
                throw new ToothlessException("Sorry, tasklist.txt seems to contain a corrupted task type.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ToothlessException("Sorry, tasks seem to have missing arguments.");
        } catch (DateTimeParseException e) {
            throw new ToothlessException("Sorry, task seems to have corrupted datetime. "
                    + "The format should be yyyy-mm-dd hh:mm");
        }
    }

    /**
     * Saves data from ArrayList of Tasks to data file.
     *
     * @param tasks ArrayList of Tasks to save into file.
     * @throws ToothlessException if saving failed.
     */
    public void saveToStorage(ArrayList<Task> tasks) throws ToothlessException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath, false);
            for (Task t : tasks) {
                fileWriter.write(t.toStorageString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new ToothlessException("Sorry, saving to tasklist.txt failed.");
        }
    }

}

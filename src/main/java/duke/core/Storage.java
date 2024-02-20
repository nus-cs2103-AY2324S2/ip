package duke.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.gui.MainWindow;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * This class represents the part of MeanDuke that handles loading and saving of data to disk.
 */
public class Storage {

    private static final String PATH = "./data/MeanDuke.txt";
    private static final File savedTaskList = new File(PATH);

    /**
     * Attempts to load a TaskList from the PATH in disk. Creates a new one if it fails to load a save.
     *
     * @return The TaskList that was loaded from disk, or a new TaskList if loading fails.
     */
    public static TaskList load(MainWindow controller) {
        try {
            TaskList taskList = new TaskList();
            Scanner s = new Scanner(savedTaskList);
            while (s.hasNext()) {
                switch (s.nextLine()) {
                case "TODO":
                    loadTodo(s, taskList);
                    break;
                case "DEADLINE":
                    loadDeadline(s, taskList);
                    break;
                case "EVENT":
                    loadEvent(s, taskList);
                    break;
                default:
                    throw new NoSuchElementException(s.nextLine());
                }
            }
            controller.showMessage("Successfully loaded save file.");
            return taskList;
        } catch (NoSuchElementException | FileNotFoundException | DateTimeParseException e) {
            controller.showMessage("Missing or corrupted save file. Creating new Task List");
            return new TaskList();
        }
    }

    private static void loadEvent(Scanner s, TaskList taskList) {
        String eventDesc = s.nextLine();
        boolean eventIsDone = parseSaveBoolean(s.nextLine());
        String[] dateTimeFrom = s.nextLine().split(";");
        String[] dateTimeTo = s.nextLine().split(";");
        taskList.add(new Event(eventDesc, eventIsDone,
                LocalDate.parse(dateTimeFrom[0]),
                dateTimeFrom.length == 1 ? null : LocalTime.parse(dateTimeFrom[1]),
                LocalDate.parse(dateTimeTo[0]),
                dateTimeTo.length == 1 ? null : LocalTime.parse(dateTimeTo[1])));
    }

    private static void loadDeadline(Scanner s, TaskList taskList) {
        String deadlineDesc = s.nextLine();
        boolean deadlineIsDone = parseSaveBoolean(s.nextLine());
        String[] dateTime = s.nextLine().split(";");
        taskList.add(new Deadline(deadlineDesc, deadlineIsDone,
                LocalDate.parse(dateTime[0]),
                dateTime.length == 1 ? null : LocalTime.parse(dateTime[1])));
    }

    private static void loadTodo(Scanner s, TaskList taskList) {
        taskList.add(new ToDo(s.nextLine(), parseSaveBoolean(s.nextLine())));
    }

    /**
     * Parses the given string into a boolean value.
     *
     * @param str The string to be parsed
     * @return true or false depending on the string
     * @throws InputMismatchException if string is not a valid boolean value
     */
    private static boolean parseSaveBoolean(String str) throws InputMismatchException {
        if (str.equals("true")) {
            return true;
        } else if (str.equals("false")) {
            return false;
        } else {
            throw new InputMismatchException();
        }
    }

    /**
     * Attempts to save the current TaskList into disk at PATH.
     *
     * @param taskList The TaskList to be saved into disk in text form.
     */
    public static void save(TaskList taskList, MainWindow controller) {
        try {
            new File("./data").mkdir();
            FileWriter fw = new FileWriter(savedTaskList);
            fw.write(taskList.saveString());
            fw.close();
        } catch (IOException e) {
            controller.showMessage("An error has occurred during saving.");
            controller.showMessage(e.getMessage());
        }
    }

}

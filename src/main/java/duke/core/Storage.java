package duke.core;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Storage {

    private static final String PATH = "./data/MeanDuke.txt";
    private static File savedTaskList = new File(PATH);

    public static TaskList load() {
        try {
            TaskList taskList = new TaskList();
            Scanner s = new Scanner(savedTaskList);
            while (s.hasNext()) {
                switch (s.nextLine()) {
                case "TODO":
                    taskList.add(new ToDo(s.nextLine(), parseSaveBoolean(s.nextLine())));
                    break;
                case "DEADLINE":
                    String deadlineDesc = s.nextLine();
                    boolean deadlineisDone = parseSaveBoolean(s.nextLine());
                    String[] dateTime = s.nextLine().split(";");
                    taskList.add(new Deadline(deadlineDesc, deadlineisDone,
                            LocalDate.parse(dateTime[0]),
                            dateTime.length == 1 ? null : LocalTime.parse(dateTime[1])));
                    break;
                case "EVENT":
                    String eventDesc = s.nextLine();
                    boolean eventIsDone = parseSaveBoolean(s.nextLine());
                    String[] dateTimeFrom = s.nextLine().split(";");
                    String[] dateTimeTo = s.nextLine().split(";");
                    taskList.add(new Event(eventDesc, eventIsDone,
                            LocalDate.parse(dateTimeFrom[0]),
                            dateTimeFrom.length == 1 ? null : LocalTime.parse(dateTimeFrom[1]),
                            LocalDate.parse(dateTimeTo[0]),
                            dateTimeTo.length == 1 ? null : LocalTime.parse(dateTimeTo[1])));
                    break;
                default:
                    throw new NoSuchElementException(s.nextLine());
                }
            }
            Ui.printMessage("Successfully loaded save file.");
            return taskList;
        } catch (NoSuchElementException | FileNotFoundException | DateTimeParseException e) {
            Ui.printMessage("Missing or corrupted save file. Creating new tasklist");
            return new TaskList();
        }
    }

    /**
     * Parses the given string into a boolean value.
     *
     * @param str The string to be parsed
     * @return true or false depending on the string
     * @throws java.util.InputMismatchException if string is not a valid boolean value
     */
    private static boolean parseSaveBoolean(String str) throws java.util.InputMismatchException {
        if (str.equals("true")) {
            return true;
        } else if (str.equals("false")) {
            return false;
        } else {
            throw new java.util.InputMismatchException();
        }
    }

    /**
     * Save the current TaskList into disk
     *
     * @param savedTaskList The File to save the data into
     */
    public static void save(TaskList tasklist) {
        try {
            new File("./data").mkdir();
            FileWriter fw = new FileWriter(savedTaskList);
            fw.write(tasklist.saveString());
            fw.close();
        } catch (IOException e) {
            Ui.printMessage("An error has occurred during saving.");
            Ui.printError(e);
        }
    }

}

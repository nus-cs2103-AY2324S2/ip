package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.gui.Main;

/**
 * Represents parser component of Duke.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("ddMMyy'T'HHmm");
    private static Parser instance = null;
    private Ui ui = null;
    private TaskList taskList = null;
    private Storage storage = null;

    public static Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }

    /**
     * Initialises parser.
     */
    public void initParser() {
        ui = Ui.getInstance();
        taskList = TaskList.getInstance();
        storage = Storage.getInstance();
    }

    // need to refactor this method, it is too long
    /**
     * Return a string representing output from processing input string and carry out corresponding task.
     *
     * @param input  User input string.
     * @throws InputException If input is invalid.
     */
    public String processInputReturnString(String input) throws InputException {
        String[] words = input.split(" ");
        // for multi-word commands
        if (words[0].equals("mark") || words[0].equals("unmark")) {
            if (isInteger(words[1])) {
                boolean isDone = words[0].equals("mark");
                int taskIndex = Integer.parseInt(words[1]);
                return taskList.setTaskDoneWithIndex(taskIndex, isDone);
            } else {
                return "Action failed: task index input is not an integer";
            }
        }

        if (words[0].equals("delete")) {
            if (isInteger(words[1])) {
                int taskIndex = Integer.parseInt(words[1]);
                Task deletedTask = taskList.deleteTask(taskIndex);
                return "Noted. I've removed this task:"
                        + "\n"
                        + "    "
                        + deletedTask
                        + "\n"
                        + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.";
            } else {
                return "Action failed: task index input is not an integer";
            }
            //return;
        }

        if (words[0].equals("todo")
                || words[0].equals("deadline")
                || words[0].equals("event")) {

            Task newTask = null;
            newTask = Task.createTask(words[0], input);
            taskList.addTask(newTask);
            return "Got it. I've added this task:"
                    + "\n"
                    + "    "
                    + newTask
                    + "\n"
                    + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.";
        }

        if (input.equals("bye")) {
            //ui.EndSession();
            storage.saveToMemory();
            return "bye";
        }

        if (input.equals("close")) {
            Main.getStage().close();
            return "close";
        }

        if (input.equals("list") || input.equals("print tasks")) {
            return ui.listTasksReturnString();
        }

        if (words[0].equals("find")) {
            taskList.findTaskWithKeyword(words);
            return ui.listFilteredTasksReturnString();
        }

        throw new CommandNotFoundException(input);
    }

    /**
     * Parses dateTime string to LocalDateTime with preset formatter.
     *
     * @param input String representing dateTime to be converted.
     * @return LocalDateTime converted from input string.
     */
    public static LocalDateTime parseDateAndTime(String input) {
        return LocalDateTime.parse(input, DATE_TIME_FORMATTER);
    }

    /**
     * Converts dataTime to duke command format.
     *
     * @param localDateTime DateTime to be converted.
     * @return String representing dateTime in command format.
     */
    public static String convertDateTimeToCommandFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
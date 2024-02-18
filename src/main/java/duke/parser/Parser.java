package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskIndexOutOfBoundsException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.gui.Main;

/**
 * Represents parser component of Duke.
 */
public class Parser {
    public static final String DATETIME_FORMAT = "ddMMyy'T'HHmm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATETIME_FORMAT);
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

    /**
     * Return a string representing output from processing input string and carry out corresponding task.
     *
     * @param input  User input string.
     * @throws InputException If input is invalid.
     */
    public String processInputReturnString(String input) throws InputException {
        String[] words = input.split(" ");
        String command = words[0];
        try {
            switch (command) {
            case "mark":
            case "unmark":
                return toggleMarkedTask(words);
            case "delete":
                return deleteTask(words);
            case "todo":
            case "deadline":
            case "event":
                return createTask(input, words);
            case "save":
                return saveToMemory();
            case "close":
                return terminateSession();
            case "list":
                return listTasks();
            case "find":
                return getTasksFilteredWithKeyword(words);
            case "distinct":
                return removeDuplicatedTasks();
            case "sort":
                return sortTasks();
            default:
                throw new CommandNotFoundException(input);
            }
        } catch (CommandNotFoundException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    private String sortTasks() {
        taskList.sortTasks();
        return ui.listTasksReturnString();
    }

    private String removeDuplicatedTasks() {
        taskList.removeDuplicatedTasks();
        return ui.listTasksReturnString();
    }

    private String getTasksFilteredWithKeyword(String[] words) {
        taskList.findTaskWithKeyword(words);
        return ui.listFilteredTasksReturnString();
    }

    private String listTasks() {
        return ui.listTasksReturnString();
    }

    private static String terminateSession() {
        Main.getStage().close();
        return "close";
    }

    private String saveToMemory() {
        storage.saveToMemory();
        return "bye";
    }

    private String createTask(String input, String[] words) throws MissingInputFieldException {
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

    private String deleteTask(String[] words) throws TaskIndexOutOfBoundsException {
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
    }

    private String toggleMarkedTask(String[] words) throws TaskIndexOutOfBoundsException {
        if (isInteger(words[1])) {
            boolean isDone = words[0].equals("mark");
            int taskIndex = Integer.parseInt(words[1]);
            return taskList.setTaskDoneWithIndex(taskIndex, isDone);
        } else {
            return "Action failed: task index input is not an integer";
        }
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
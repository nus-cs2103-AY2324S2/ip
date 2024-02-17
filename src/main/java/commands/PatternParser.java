package commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.RyanGoslingBadFormatException;
import exceptions.RyanGoslingException;
import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import tasks.Todo;
import utilities.Storage;
import utilities.TaskList;

/**
 * Provides static methods for parsing different types of task
 * commands using regular expressions.
 * <p>
 * It includes methods for parsing "todo," "deadline," "event," and "find" commands, as well as a utility
 * method for adding tasks
 * to a list from a formatted string.
 */
public class PatternParser {
    private static final Pattern todoPattern = Pattern.compile("todo (.*?)");
    private static final Pattern deadlinePattern = Pattern.compile("deadline (.*?) /by (.*?) (.*?)");
    private static final Pattern eventPattern = Pattern.compile("event (.*?) /from (.*?) (.*?) /to (.*?) (.*?)");
    private static final Pattern findPattern = Pattern.compile("^find\\s+(.*?)$");
    private static final String fileInputPattern = "^\\s*(\\w+)\\s*\\|\\s*(\\d+)\\s*\\|\\s*(\\S+)\\s*\\|\\s*"
            + "(\\S+)\\s*"
            + "\\|\\s*(\\S+)\\s*\\|\\"
            + "s*(\\S+)\\s*\\|\\s*(\\S+)\\s*$";

    private static final String updatePattern = "^update\\s+(\\d+)\\s+(\\S+)\\s+(\\d{4}-\\d{2}-\\d{2}|NA)"
            + "\\s+(\\d{4}-\\d{2}-"
            + "\\d{2}|NA)\\s+(\\d{4}|NA)\\s+(\\d{4}|NA)$";

    /**
     * Parses a "todo" command and adds the corresponding task to the task list.
     *
     * @param taskToParse    The input string containing the "todo" command.
     * @param taskListManager The TaskList instance managing the list of tasks.
     * @param taskLoader     The Storage instance responsible for loading and saving tasks.
     * @return A response message indicating the success or failure of the parsing operation.
     * @throws RyanGoslingException If the input string does not match the expected "todo" pattern.
     */
    public static String todoParser(String taskToParse, TaskList taskListManager, Storage taskLoader)
            throws RyanGoslingException {
        Matcher matcher = todoPattern.matcher(taskToParse);
        if (!matcher.matches()) {
            throw new RyanGoslingException("Incomplete todo command, todo <event>");
        }
        String responseReturn = taskListManager.add(new Todo(matcher.group(1)));
        taskListManager.writeToFile(taskLoader);
        return responseReturn;
    }

    /**
     * Parses a "deadline" command and adds the corresponding task to the task list.
     *
     * @param taskToParse    The input string containing the "deadline" command.
     * @param taskListManager The TaskList instance managing the list of tasks.
     * @param taskLoader     The Storage instance responsible for loading and saving tasks.
     * @return A response message indicating the success or failure of the parsing operation.
     * @throws RyanGoslingException If the input string does not match the expected pattern.
     */
    public static String deadlineParser(String taskToParse, TaskList taskListManager, Storage taskLoader)
            throws RyanGoslingException {
        Matcher matcher = deadlinePattern.matcher(taskToParse);
        if (!matcher.matches()) {
            throw new RyanGoslingException("Incomplete deadline command, " + "deadline <event> /by <date> <time> "
                                                   + "\n If no specific time, leave time as 2359");
        }

        String responseReturn = taskListManager.add(new Deadline(matcher.group(1), matcher.group(2),
                                                                 matcher.group(3)));
        taskListManager.writeToFile(taskLoader);
        return responseReturn;
    }

    /**
     * Parses an "event" command and adds the corresponding task to the task list.
     *
     * @param taskToParse    The input string containing the "event" command.
     * @param taskListManager The TaskList instance managing the list of tasks.
     * @param taskLoader     The Storage instance responsible for loading and saving tasks.
     * @return A response message indicating the success or failure of the parsing operation.
     * @throws RyanGoslingException If the input string does not match the expected "event" pattern.
     */
    public static String eventParser(String taskToParse, TaskList taskListManager, Storage taskLoader)
            throws RyanGoslingException {
        Matcher matcher = eventPattern.matcher(taskToParse);
        if (!matcher.matches()) {
            throw new RyanGoslingException("Incomplete event command, "
                                                   + "event <event> /from <date> <time> /to <date> <time>\n"
                                                   + "If no time, leave time as 2359");
        }

        String responseReturn = taskListManager.add(new Events(matcher.group(1), matcher.group(2), matcher.group(3),
                                                               matcher.group(4), matcher.group(5)));
        taskListManager.writeToFile(taskLoader);
        return responseReturn;
    }

    /**
     * Parses a "find" command and searches for tasks containing the specified keyword.
     *
     * @param taskToParse    The input string containing the "find" command.
     * @param taskListManager The TaskList instance managing the list of tasks.
     * @return A response message containing the tasks matching the specified keyword.
     * @throws RyanGoslingException If the input string does not match the expected "find" pattern.
     */
    public static String findParser(String taskToParse, TaskList taskListManager) throws RyanGoslingException {
        Matcher matcher = findPattern.matcher(taskToParse);
        if (matcher.matches()) {
            return taskListManager.findTasks(matcher.group(1));
        } else {
            throw new RyanGoslingException("Incomplete find command! find <task_word>");
        }
    }

    /**
     * Adds a task to the list based on the formatted input line.
     *
     * @param listOfTasks The list of tasks to which the task will be added.
     * @param inputLine   The formatted input line containing task details.
     * @throws RyanGoslingException If the input line does not match the expected format.
     */
    public static void addTasktoList(ArrayList<Task> listOfTasks, String inputLine) throws RyanGoslingException {
        Pattern regex = Pattern.compile(fileInputPattern);
        Matcher matcher = regex.matcher(inputLine);
        if (!matcher.matches()) {
            throw new RyanGoslingException("Task lists stored in hard drive is not in expected format!\n"
                                                   + "It should follow (T/E/D) | (0/1) | Description | dateFrom | "
                                                   + "timeFrom "
                                                   + "| dateTo | timeTo");
        }
        String typeOfTask = matcher.group(1);
        int isTaskDone = Integer.parseInt(matcher.group(2));
        String taskDescription = matcher.group(3);
        String dateFrom = matcher.group(4);
        String timeFrom = matcher.group(5);
        String dateTo = matcher.group(6);
        String timeTo = matcher.group(7);
        switch (typeOfTask) {
        case "T":
            listOfTasks.add(new Todo(taskDescription, isTaskDone));
            break;
        case "D":
            listOfTasks.add(new Deadline(taskDescription, dateFrom, timeFrom, isTaskDone));
            break;
        case "E":
            listOfTasks.add(new Events(taskDescription, dateFrom, timeFrom, dateTo, timeTo, isTaskDone));
            break;
        default:

        }
    }

    /**
     * Parses and updates task information based on the provided input line.
     *
     * @param taskListManager The task list manager.
     * @param inputLine       The input line containing the update information.
     * @param taskLoader      The task loader for saving changes.
     * @return A string indicating the result of the update operation.
     * @throws RyanGoslingException If there is an issue with the update command or input.
     */
    public static String updateTaskParser(TaskList taskListManager, String inputLine, Storage taskLoader)
            throws RyanGoslingException {
        Pattern pattern = Pattern.compile(updatePattern);
        Matcher matcher = pattern.matcher(inputLine);
        if (!matcher.matches()) {
            throw new RyanGoslingException("Invalid update command!\n update <index> <new_name> <dateFrom> <dateTo> "
                                                   + "<timeFrom> <timeTo>\nIrrelevant fields can be input as NA");
        }
        String indexNumber = matcher.group(1);
        String newTaskName = matcher.group(2);
        String dateFrom = handleNA(matcher.group(3));
        String dateTo = handleNA(matcher.group(4));
        String timeFrom = handleNA(matcher.group(5));
        String timeTo = handleNA(matcher.group(6));

        StringBuilder responseReturn = new StringBuilder();
        int indexOfTask = Integer.parseInt(indexNumber) - 1;
        if (!taskListManager.validateIndex(indexOfTask)) {
            throw new RyanGoslingException("Invalid range of index!");
        }

        if (!newTaskName.equals("NA")) {
            taskListManager.updateNameOfTask(indexOfTask, newTaskName, taskLoader);
            responseReturn.append("Successfully updated task name to \n").append(newTaskName);
        }
        String taskType = taskListManager.getTaskType(indexOfTask);
        switch (taskType) {
        case "T":
            boolean doesTodoHaveInvalidDateTimeInput = !(dateFrom.equals("NA") && dateTo.equals("NA")
                                                                 && timeFrom.equals("NA") && timeTo.equals("NA"));
            if (doesTodoHaveInvalidDateTimeInput) {
                throw new RyanGoslingException(responseReturn + "\nTodo cannot have any non NA time/date fields!");
            }
            break;
        case "D":
            taskListManager.updateDatesOfTask(indexOfTask, new String[]{dateFrom, "NA"}, taskLoader);
            taskListManager.updateTimesOfTask(indexOfTask, new String[]{timeFrom, "NA"}, taskLoader);
            break;
        case "E":
            taskListManager.updateDatesOfTask(indexOfTask, new String[]{dateFrom, dateTo}, taskLoader);
            taskListManager.updateTimesOfTask(indexOfTask, new String[]{timeFrom, timeTo}, taskLoader);
            break;
        default:

        }
        responseReturn.append("\nUpdated all fields (if any were input) successfully!");
        return responseReturn.toString();
    }

    // Helper method to handle "NA", with help from ChatGPT
    private static String handleNA(String value) {
        return (value == null) ? "NA" : value;
    }

    /**
     * Checks for additional spaces, trailing spaces, or empty input in the command.
     *
     * @param inputLine The input command to check.
     * @throws RyanGoslingException If the input command has formatting issues.
     */
    public static void inputSpacesChecker(String inputLine) throws RyanGoslingException {
        if (inputLine.startsWith(" ") || inputLine.endsWith(" ")) {
            throw new RyanGoslingBadFormatException("Invalid command, additional spaces at the beginning or end.");
        }

        if (inputLine.matches(".*\\s{2,}.*")) {
            throw new RyanGoslingBadFormatException("Invalid command, multiple spaces between words.");
        }

        if (inputLine.isEmpty()) {
            throw new RyanGoslingBadFormatException("You typed... nothing?");
        }

    }





}

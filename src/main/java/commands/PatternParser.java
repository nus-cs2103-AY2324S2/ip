package commands;

import exceptions.RyanGoslingException;
import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import tasks.Todo;
import utilities.Storage;
import utilities.TaskList;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The PatternParser class provides static methods for parsing different types of task commands using regular expressions.
 * It includes methods for parsing "todo," "deadline," "event," and "find" commands, as well as a utility method for adding tasks
 * to a list from a formatted string.
 */
public class PatternParser {
    private static final Pattern todoPattern = Pattern.compile("todo (.*?)");
    private static final Pattern deadlinePattern = Pattern.compile("deadline (.*?) /by (.*?) (.*?)");
    private static final Pattern eventPattern = Pattern.compile("event (.*?) /from (.*?) (.*?) /to (.*?) (.*?)");
    private static final Pattern findPattern = Pattern.compile("event (.*?) /from (.*?) (.*?) /to (.*?) (.*?)");
    private static final String fileInputPattern = "^\\s*(\\w+)\\s*\\|\\s*(\\d+)\\s*\\|\\s*(\\S+)\\s*\\|\\s*"
            + "(\\S+)\\s*"
            + "\\|\\s*(\\S+)\\s*\\|\\"
            + "s*(\\S+)\\s*\\|\\s*(\\S+)\\s*$";

    /**
     * Parses a "todo" command and adds the corresponding task to the task list.
     *
     * @param taskToParse    The input string containing the "todo" command.
     * @param taskListManager The TaskList instance managing the list of tasks.
     * @param taskLoader     The Storage instance responsible for loading and saving tasks.
     * @return A response message indicating the success or failure of the parsing operation.
     * @throws RyanGoslingException If the input string does not match the expected "todo" pattern.
     */
    public static String todoParser(String taskToParse, TaskList taskListManager, Storage taskLoader) throws RyanGoslingException {
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
     * @throws RyanGoslingException If the input string does not match the expected "deadline" pattern.
     */
    public static String deadlineParser(String taskToParse, TaskList taskListManager, Storage taskLoader) throws RyanGoslingException {
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
    public static String eventParser(String taskToParse, TaskList taskListManager, Storage taskLoader) throws RyanGoslingException {
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
     * Utility method to add a task to the list based on the formatted input line.
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





}

package commands;

import exceptions.RyanGoslingException;
import tasks.Deadline;
import tasks.Events;
import tasks.Todo;
import utilities.Storage;
import utilities.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternParser {
    private static final Pattern todoPattern = Pattern.compile("todo (.*?)");
    private static final Pattern deadlinePattern = Pattern.compile("deadline (.*?) /by (.*?) (.*?)");
    private static final Pattern eventPattern = Pattern.compile("event (.*?) /from (.*?) (.*?) /to (.*?) (.*?)");
    private static final Pattern findPattern = Pattern.compile("event (.*?) /from (.*?) (.*?) /to (.*?) (.*?)");




    public static String todoParser(String taskToParse, TaskList taskListManager, Storage taskLoader) throws RyanGoslingException {
        Matcher matcher = todoPattern.matcher(taskToParse);
        if (!matcher.matches()) {
            throw new RyanGoslingException("Incomplete todo command, todo <event>");
        }
        String responseReturn = taskListManager.add(new Todo(matcher.group(1)));
        taskListManager.writeToFile(taskLoader);
        return responseReturn;
    }

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

    public static String findParser(String taskToParse, TaskList taskListManager) throws RyanGoslingException {
        Matcher matcher = findPattern.matcher(taskToParse);
        if (matcher.matches()) {
            return taskListManager.findTasks(matcher.group(1));
        } else {
            throw new RyanGoslingException("Incomplete find command! find <task_word>");
        }
    }
}

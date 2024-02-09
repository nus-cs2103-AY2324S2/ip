package numerator;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import numerator.exceptions.NumeratorException;
import numerator.exceptions.parser.InputFormatException;
import numerator.exceptions.parser.InputNotRecognisedException;
import numerator.exceptions.parser.TaskIndexOutOfBoundsException;
import numerator.task.Task;
import numerator.task.TaskList;

/**
 * Parses the input from the user and performs the corresponding action
 */
public class Parser {

    public static final String BYE_STRING = "Bye! Just close the window to exit";

    /**
     * Parses the input and performs the corresponding action
     *
     * @param input    the input from the user
     * @param taskList the list of tasks
     * @param storage  the storage object
     * @return the response to the user
     * @throws NumeratorException if the input is not recognised
     */
    public static String parseArguments(String input, TaskList taskList, Storage storage) throws NumeratorException {

        assert taskList != null;
        assert storage != null;
        assert input != null;


        if (input.equals("bye")) {
            return BYE_STRING;

        } else if (input.startsWith("mark")) {
            return parseMark(input, taskList, storage);

        } else if (input.startsWith("unmark")) {
            return parseUnmark(input, taskList, storage);

        } else if (input.startsWith("delete")) {
            return parseDelete(input, taskList, storage);

        } else if (input.startsWith("todo")) {
            return parseTodo(input, taskList, storage);

        } else if (input.startsWith("deadline")) {
            return parseDeadline(input, taskList, storage);

        } else if (input.startsWith("event")) {
            return parseEvent(input, taskList, storage);

        } else if (input.equals("list")) {
            return taskList.toString();

        } else if (input.startsWith("find")) {
            return parseFind(input, taskList);
        } else {
            throw new InputNotRecognisedException();
        }
    }

    private static String parseFind(String input, TaskList taskList) throws InputFormatException {
        Pattern p = Pattern.compile("find (\\S+.*)");
        Matcher m = p.matcher(input);
        if (!m.find()) {
            throw new InputFormatException("Please use the format: find <keyword>");
        } else {
            String keyword = m.group(1);
            return taskList.findTasks(keyword);
        }
    }

    private static String parseEvent(String input, TaskList taskList, Storage storage) throws NumeratorException {
        Pattern p = Pattern.compile("event (\\S+.*) /from (\\S+.*) /to (\\S+.*)");
        Matcher m = p.matcher(input);
        if (!m.find()) {
            throw new InputFormatException("Please use the format: event <task> /from <date> /to <date>");
        } else {
            String taskDesc = m.group(1);
            String from = m.group(2);
            String to = m.group(3);
            Task t = taskList.addEvent(taskDesc, from, to);
            storage.save(taskList);
            return taskList.getAddTaskString(t);
        }
    }

    private static String parseDeadline(String input, TaskList taskList, Storage storage) throws NumeratorException {
        Pattern p = Pattern.compile("deadline (\\S+.*) /by (\\S+.*)");
        Matcher m = p.matcher(input);
        if (!m.find()) {
            throw new InputFormatException("Please use the format: deadline <task> /by <date>");
        } else {
            String taskDesc = m.group(1);
            String by = m.group(2);
            try {
                Task t = taskList.addDeadline(taskDesc, by);
                storage.save(taskList);
                return taskList.getAddTaskString(t);
            } catch (DateTimeParseException e) {
                throw new InputFormatException("The date should be in the format: yyyy/MM/dd");
            }
        }
    }

    private static String parseTodo(String input, TaskList taskList, Storage storage) throws NumeratorException {
        Pattern p = Pattern.compile("todo (\\S+.*)");
        Matcher m = p.matcher(input);
        if (!m.find()) {
            throw new InputFormatException("Please use the format: todo <task>");
        } else {
            String taskDesc = m.group(1);
            Task t = taskList.addToDo(taskDesc);
            storage.save(taskList);
            return taskList.getAddTaskString(t);
        }
    }

    private static String parseDelete(String input, TaskList taskList, Storage storage) throws NumeratorException {
        try {
            Pattern p = Pattern.compile("delete (\\d+)");
            Matcher m = p.matcher(input);
            if (!m.find()) {
                throw new InputFormatException("Please use the format: delete <task number>");
            } else {
                int taskNum = Integer.parseInt(m.group(1)) - 1;
                Task t = taskList.removeTask(taskNum);
                storage.save(taskList);
                return taskList.getDeleteTaskString(t);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException("Task does not exist");
        }
    }

    private static String parseUnmark(String input, TaskList taskList, Storage storage) throws NumeratorException {
        try {
            Pattern p = Pattern.compile("unmark (\\d+)");
            Matcher m = p.matcher(input);
            if (!m.find()) {
                throw new InputFormatException("Please use the format: unmark <task number>");
            } else {
                int taskNum = Integer.parseInt(m.group(1)) - 1;
                taskList.markAsUndone(taskNum);
                storage.save(taskList);
                return "OK, I've marked this task as not done yet:" + taskList.getTaskAtIndex(taskNum);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException("Task does not exist");
        }
    }

    private static String parseMark(String input, TaskList taskList, Storage storage) throws NumeratorException {
        try {
            Pattern p = Pattern.compile("mark (\\d+)");
            Matcher m = p.matcher(input);
            if (!m.find()) {
                throw new InputFormatException("Please use the format: mark <task number>");
            } else {
                int taskNum = Integer.parseInt(m.group(1)) - 1;
                taskList.markAsDone(taskNum);
                storage.save(taskList);
                return "Nice! I've marked this task as done:\n" + taskList.getTaskAtIndex(taskNum);
            }

        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException("Task does not exist");
        }
    }

}

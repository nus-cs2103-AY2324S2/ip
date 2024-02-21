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
 * Parses the input from the user and performs the corresponding action.
 */
public class Parser {

    public static final String BYE_STRING = "Bye! Just close the window to exit";
    private static final String INPUT_BYE = "bye";
    private static final String INPUT_MARK = "mark";
    private static final String INPUT_UNMARK = "unmark";
    private static final String INPUT_DELETE = "delete";
    private static final String INPUT_TODO = "todo";
    private static final String INPUT_DEADLINE = "deadline";
    private static final String INPUT_EVENT = "event";
    private static final String INPUT_LIST = "list";
    private static final String INPUT_FIND = "find";
    private static final String INPUT_TAG = "tag";
    private static final String INPUT_UNTAG = "untag";


    private Parser() {
    }

    /**
     * Parses the input and performs the corresponding action.
     *
     * @param input    the input from the user.
     * @param taskList the list of tasks.
     * @param storage  the storage object.
     * @return the response to the user.
     * @throws NumeratorException if the input is not recognised.
     */
    public static String parseArguments(String input, TaskList taskList, Storage storage) throws NumeratorException {

        assert taskList != null;
        assert storage != null;
        assert input != null;

        final boolean isToAddTask = input.startsWith(INPUT_TODO)
                || input.startsWith(INPUT_DEADLINE)
                || input.startsWith(INPUT_EVENT);

        final boolean isToModifyTask = input.startsWith(INPUT_MARK)
                || input.startsWith(INPUT_UNMARK)
                || input.startsWith(INPUT_DELETE)
                || input.startsWith(INPUT_TAG)
                || input.startsWith(INPUT_UNTAG);


        final boolean isUiInteraction = input.equals(INPUT_BYE)
                || input.equals(INPUT_LIST)
                || input.isBlank()
                || input.startsWith(INPUT_FIND);

        if (isUiInteraction) {
            return parseUiInteractionReducer(input, taskList);
        } else if (isToModifyTask) {
            return parseModifyTaskReducer(input, taskList, storage);
        } else if (isToAddTask) {
            return parseAddTaskReducer(input, taskList, storage);
        } else {
            throw new InputNotRecognisedException();
        }
    }

    private static String parseUiInteractionReducer(String input, TaskList taskList) throws NumeratorException {
        if (input.equals(INPUT_BYE)) {
            return BYE_STRING;
        } else if (input.equals(INPUT_LIST)) {
            return taskList.toString();
        } else if (input.isBlank()) {
            throw new InputFormatException("Please enter something");
        } else if (input.startsWith(INPUT_FIND)) {
            return parseFind(input, taskList);
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    private static String parseModifyTaskReducer(String input, TaskList taskList, Storage storage)
            throws NumeratorException {
        if (input.startsWith(INPUT_MARK)) {
            return parseMark(input, taskList, storage);

        } else if (input.startsWith(INPUT_UNMARK)) {
            return parseUnmark(input, taskList, storage);
        } else if (input.startsWith(INPUT_DELETE)) {
            return parseDelete(input, taskList, storage);
        } else if (input.startsWith(INPUT_TAG)) {
            return parseTag(input, taskList, storage);
        } else if (input.startsWith(INPUT_UNTAG)) {
            return parseUntag(input, taskList, storage);
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }


    private static String parseAddTaskReducer(String input, TaskList taskList, Storage storage)
            throws NumeratorException {
        if (input.startsWith(INPUT_TODO)) {
            return parseTodo(input, taskList, storage);
        } else if (input.startsWith(INPUT_DEADLINE)) {
            return parseDeadline(input, taskList, storage);
        } else if (input.startsWith(INPUT_EVENT)) {
            return parseEvent(input, taskList, storage);
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    private static String parseUntag(String input, TaskList taskList, Storage storage)
            throws NumeratorException {
        Matcher m = getMatcher(
                input,
                "untag (\\d+) (\\S+.*)",
                "Please use the format: untag <task number> <tag>"
        );
        int taskNum = Integer.parseInt(m.group(1)) - 1;
        String tag = m.group(2);
        taskList.untagTask(taskNum, tag);
        storage.saveFile(taskList);
        return "OK, I've untagged " + tag + " from the task.\n" + taskList.getTaskAtIndex(taskNum);
    }

    private static String parseTag(String input, TaskList taskList, Storage storage)
            throws NumeratorException {
        Matcher m = getMatcher(
                input,
                "tag (\\d+) (\\S+.*)",
                "Please use the format: tag <task number> <tag>"
        );
        int taskNum = Integer.parseInt(m.group(1)) - 1;
        String tag = m.group(2);
        taskList.tagTask(taskNum, tag);
        storage.saveFile(taskList);
        return "OK, I've tagged this task with #" + tag + ":\n" + taskList.getTaskAtIndex(taskNum);
    }


    private static String parseFind(String input, TaskList taskList)
            throws InputFormatException {
        Matcher m = getMatcher(
                input,
                "find (\\S+.*)",
                "Please use the format: find <keyword>"
        );
        String keyword = m.group(1);
        return taskList.findTasks(keyword);

    }

    private static String parseEvent(String input, TaskList taskList, Storage storage)
            throws NumeratorException {
        Matcher m = getMatcher(
                input,
                "event (\\S+.*) /from (\\S+.*) /to (\\S+.*)",
                "Please use the format: event <task> /from <date> /to <date>"
        );
        String taskDesc = m.group(1);
        String from = m.group(2);
        String to = m.group(3);
        Task t = taskList.addEvent(taskDesc, from, to);
        storage.saveFile(taskList);
        return taskList.getAddTaskString(t);
    }


    private static String parseDeadline(String input, TaskList taskList, Storage storage)
            throws NumeratorException {
        Matcher m = getMatcher(
                input,
                "deadline (\\S+.*) /by (\\S+.*)",
                "Please use the format: deadline <task> /by <date>"
        );

        String taskDesc = m.group(1);
        String by = m.group(2);
        try {
            Task t = taskList.addDeadline(taskDesc, by);
            storage.saveFile(taskList);
            return taskList.getAddTaskString(t);
        } catch (DateTimeParseException e) {
            throw new InputFormatException("The date should be in the format: yyyy/MM/dd");
        }

    }

    private static String parseTodo(String input, TaskList taskList, Storage storage)
            throws NumeratorException {
        Matcher m = getMatcher(
                input,
                "todo (\\S+.*)",
                "Please use the format: todo <task>"
        );
        String taskDesc = m.group(1);
        Task t = taskList.addToDo(taskDesc);
        storage.saveFile(taskList);
        return taskList.getAddTaskString(t);
    }

    private static String parseDelete(String input, TaskList taskList, Storage storage)
            throws NumeratorException {
        try {
            Matcher m = getMatcher(
                    input,
                    "delete (\\d+)",
                    "Please use the format: delete <task number>"
            );
            int taskNum = Integer.parseInt(m.group(1)) - 1;
            Task t = taskList.removeTask(taskNum);
            storage.saveFile(taskList);
            return taskList.getDeleteTaskString(t);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    private static String parseUnmark(String input, TaskList taskList, Storage storage)
            throws NumeratorException {
        try {
            Matcher m = getMatcher(
                    input,
                    "unmark (\\d+)",
                    "Please use the format: unmark <task number>"
            );
            int taskNum = Integer.parseInt(m.group(1)) - 1;
            taskList.markAsUndone(taskNum);
            storage.saveFile(taskList);
            return "OK, I've marked this task as not done yet:\n" + taskList.getTaskAtIndex(taskNum);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException();
        }
    }


    private static String parseMark(String input, TaskList taskList, Storage storage)
            throws NumeratorException {
        try {
            Matcher m = getMatcher(
                    input,
                    "mark (\\d+)",
                    "Please use the format: mark <task number>"
            );
            int taskNum = Integer.parseInt(m.group(1)) - 1;
            taskList.markAsDone(taskNum);
            storage.saveFile(taskList);
            return "Nice! I've marked this task as done:\n" + taskList.getTaskAtIndex(taskNum);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException();
        }
    }


    private static Matcher getMatcher(String input, String regex, String formatError)
            throws InputFormatException {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if (!m.find()) {
            throw new InputFormatException(formatError);
        }
        return m;
    }


}

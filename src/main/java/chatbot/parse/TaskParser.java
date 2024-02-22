package chatbot.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.task.ToDo;
import chatbot.task.exception.InvalidTaskStringException;

/**
 * Parses the stored input line into a {@link Task}.
 *
 * @author Titus Chew
 */
public final class TaskParser {
    /** Stores the pattern to match a {@link TaskList} item. */
    private static final Pattern TASK_ITEM_PATTERN = Pattern.compile("\\d+\\.(?<task>.*)");

    /**
     * Parses a {@link TaskList} item from a human-readable string.
     *
     * @param readableString The {@link TaskList} item as a human-readable string.
     * @return The parsed {@link Task}.
     * @throws InvalidTaskStringException If the regex doesn't match the pattern.
     */
    public static Task parseTaskListItem(String readableString) throws InvalidTaskStringException {
        Matcher matcher = TASK_ITEM_PATTERN.matcher(readableString);

        if (!matcher.find()) {
            throw new InvalidTaskStringException();
        }

        String parsedString = matcher.group("task").trim();
        return TaskParser.parseTask(parsedString);
    }

    /**
     * Parses a {@link Task} from a human-readable string.
     *
     * @param readableString The task as a human-readable string.
     * @return The parsed {@link Task}.
     * @throws InvalidTaskStringException If the regex doesn't match the pattern.
     */
    private static Task parseTask(String readableString) throws InvalidTaskStringException {
        // note that the order of the tasks matters,
        // and should be from most specific pattern to the least specific pattern.
        if (Event.isMatchingEvent(readableString)) {
            return Event.parseEvent(readableString);
        } else if (Deadline.isMatchingDeadline(readableString)) {
            return Deadline.parseDeadline(readableString);
        } else if (ToDo.isMatchingToDo(readableString)) {
            return ToDo.parseToDo(readableString);
        } else {
            // this should not happen unless the user modifies the save file with the wrong format.
            throw new InvalidTaskStringException();
        }
    }
}

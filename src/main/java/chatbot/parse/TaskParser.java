package chatbot.parse;

import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.task.ToDo;
import chatbot.task.exception.InvalidTaskStringException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses the stored input line into a {@link Task}.
 *
 * @author Titus Chew
 */
public final class TaskParser {
    /** Stores the pattern to match a {@link TaskList} item. */
    public static Pattern TASK_ITEM_PATTERN = Pattern.compile("\\d+\\.(?<task>.*)");

    /**
     * Parses a {@link TaskList} item from a human-readable string.
     *
     * @param readableString the {@link TaskList} item as a human-readable string
     * @return the parsed @link Task}
     * @throws InvalidTaskStringException If the regex doesn't match the pattern.
     */
    public static Task parseTaskListItem(String readableString) throws InvalidTaskStringException {
        Matcher matcher = TASK_ITEM_PATTERN.matcher(readableString);

        if (matcher.find()) {
            String parsedString = matcher.group("task").trim();
            return TaskParser.parseTask(parsedString);
        } else {
            throw new InvalidTaskStringException();
        }
    }

    /**
     * Parses a {@link Task} from a human-readable string.
     *
     * @param readableString the task as a human-readable string
     * @return the parsed {@link Task}
     * @throws InvalidTaskStringException If the regex doesn't match the pattern
     */
    private static Task parseTask(String readableString) throws InvalidTaskStringException {
        // note that the order of the tasks matters,
        // and should be from most specific to least specific.
        if (Event.matchesEvent(readableString)) {
            return Event.parseEvent(readableString);
        } else if (Deadline.matchesDeadline(readableString)) {
            return Deadline.parseDeadline(readableString);
        } else if (ToDo.matchesToDo(readableString)) {
            return ToDo.parseToDo(readableString);
        }

        throw new InvalidTaskStringException();
    }
}

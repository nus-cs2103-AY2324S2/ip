package seedu.duke.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.common.Messages;
import seedu.duke.exception.StorageOperationException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;


/**
 * The TaskDecoder class implements a method that would decode string and parse it into Task object
 */
public class TaskDecoder {
    private static final Pattern DEADLINE_SAVE_FORMAT = Pattern.compile(
            "D \\| (?<hasDone>(0|1)) \\| (?<taskDescription>.*) \\| (?<deadline>.*)");
    private static final Pattern TODO_SAVE_FORMAT = Pattern.compile(
            "T \\| (?<hasDone>(0|1)) \\| (?<taskDescription>.*)");
    private static final Pattern EVENT_SAVE_FORMAT = Pattern.compile("E \\| (?<hasDone>(0|1)) \\|"
            + " (?<taskDescription>.*) \\| (?<startDate>.*)\\-(?<endDate>.*)");
    private static final DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("MM dd yyyy HH:mm");

    /**
     * Returns a task object based on string using pre-defined pattern
     *
     * @param taskString The task string to be decoded
     * @return the Task object that the string decoded into
     * @throws StorageOperationException If the string does not match any of the pattern or contains invalid contents
     */
    public static Task decodeTask(String taskString) throws StorageOperationException {
        Matcher deadlineMatcher = DEADLINE_SAVE_FORMAT.matcher(taskString.trim());
        Matcher todoMatcher = TODO_SAVE_FORMAT.matcher(taskString.trim());
        Matcher eventMatcher = EVENT_SAVE_FORMAT.matcher(taskString.trim());


        try {
            if (deadlineMatcher.matches()) {
                return decodeDeadline(deadlineMatcher);
            } else if (eventMatcher.matches()) {
                return decodeEvent(eventMatcher);
            } else if (todoMatcher.matches()) {
                return decodeTodo(todoMatcher);
            } else {
                throw new StorageOperationException(
                        String.format(Messages.MESSAGE_FAILED_STORAGE, "File may be corrupted"));
            }
        } catch (DateTimeParseException e) {
            throw new StorageOperationException(
                    String.format(Messages.MESSAGE_FAILED_STORAGE, "File may be corrupted"));
        }
    }

    private static Task decodeTodo(Matcher todoMatcher) {
        boolean hasDone = !todoMatcher.group("hasDone").equals("0");
        String taskDescription = todoMatcher.group("taskDescription");
        return new Todo(taskDescription, hasDone);
    }

    private static Task decodeDeadline(Matcher deadlineMatcher) {
        boolean hasDone = !deadlineMatcher.group("hasDone").equals("0");
        String taskDescription = deadlineMatcher.group("taskDescription");


        LocalDateTime deadline = LocalDateTime.parse(deadlineMatcher.group("deadline"), datetimeFormatter);


        return new Deadline(taskDescription, hasDone, deadline);
    }

    private static Task decodeEvent(Matcher eventMatcher) {
        boolean hasDone = !eventMatcher.group("hasDone").equals("0");
        String taskDescription = eventMatcher.group("taskDescription");


        LocalDateTime startDate = LocalDateTime.parse(eventMatcher.group("startDate"), datetimeFormatter);
        LocalDateTime endDate = LocalDateTime.parse(eventMatcher.group("endDate"), datetimeFormatter);


        return new Event(taskDescription, hasDone, startDate, endDate);
    }
}

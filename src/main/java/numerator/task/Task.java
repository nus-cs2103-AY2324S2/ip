package numerator.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import numerator.exceptions.parser.InputFormatException;

/**
 * Represents a task.
 */
public abstract class Task {
    protected boolean isDone;
    final String description;
    private final Set<String> tags = new HashSet<>();

    /**
     * Constructs a task with the specified description.
     *
     * @param description should contain information about the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with the specified description and tags.
     *
     * @param description should contain information about the task.
     * @param isDone      whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Parses a string in the format of "yyyy/MM/dd HH:mm" or "yyyy/MM/dd" to a LocalDateTime object.
     *
     * @param datetimeString a string in the format of "yyyy/MM/dd HH:mm" or "yyyy/MM/dd".
     * @return a LocalDateTime object.
     * @throws DateTimeParseException if the date and time is not in the correct format.
     */
    static LocalDateTime parseStringToLocalDatetime(String datetimeString) throws DateTimeParseException {
        assert datetimeString != null;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        try {
            return LocalDateTime.parse(datetimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(datetimeString + " 00:00", dateTimeFormatter);
        }
    }

    /**
     * Parses a LocalDateTime object to a string in the format of "yyyy/MM/dd HH:mm".
     *
     * @param localDateTime a LocalDateTime object.
     * @return a string in the format of "yyyy/MM/dd HH:mm".
     * @throws DateTimeException if the date and time is not in the correct format.
     */
    protected static String parseLocalDateTimeToString(LocalDateTime localDateTime) throws DateTimeException {
        assert localDateTime != null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return localDateTime.format(formatter);
    }


    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }


    /**
     * Returns a "X" (done) or " " (undone) for the status of the task.
     *
     * @return "X" or " " representing the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string with task details to be saved in the file.
     *
     * @return a string to be saved in the file.
     */
    public abstract String getSaveString();

    /**
     * Adds a tag to the task.
     *
     * @param tag the tag to be added.
     */
    public void addTag(String tag) throws InputFormatException {
        if (tag.isEmpty()) {
            return;
        }
        Pattern p = Pattern.compile("\\W");
        if (p.matcher(tag).find()) {
            throw new InputFormatException(
                    "Tag can only contain alphanumeric characters without spaces"
            );
        }
        this.tags.add(tag);
    }

    /**
     * Adds a set of tags to the task.
     *
     * @param tags the set of tags to be added.
     */
    public void addTags(Collection<String> tags) {
        this.tags.addAll(tags);
    }

    /**
     * Removes a tag from the task.
     *
     * @param tag the tag to be removed.
     */
    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    /**
     * Returns a string representation of the tags.
     *
     * @return a string representation of the tags.
     */
    public String getTagsString() {
        if (tags.isEmpty()) {
            return " ";
        }
        StringBuilder z = new StringBuilder();
        for (String tag : tags) {
            z.append(" #").append(tag);
        }
        return z.toString().strip();
    }

    /**
     * Returns a string representation of the tags for saving.
     *
     * @return a string representation of the tags.
     */
    public String getTagsSaveString() {
        if (tags.isEmpty()) {
            return " ";
        }
        StringBuilder z = new StringBuilder();
        for (String tag : tags) {
            z.append(" ").append(tag);
        }
        return z.toString().strip();
    }

}

package lindi.task;

import java.time.format.DateTimeFormatter;

/**
 * Abstract representation for tasks
 */
public abstract class Task { // Adapted from Course Website
    protected static final String DISPLAY_DT_FORMAT_STRING = "MMM dd yyyy HH:mm";
    protected static final String SAVE_LOAD_DT_FORMAT_STRING = "yyyy-MM-dd-HH-mm";
    protected static final DateTimeFormatter DISPLAY_DATETIME_FORMAT =
            DateTimeFormatter.ofPattern(DISPLAY_DT_FORMAT_STRING);
    protected static final DateTimeFormatter SAVE_LOAD_DATETIME_FORMAT =
            DateTimeFormatter.ofPattern(SAVE_LOAD_DT_FORMAT_STRING);

    protected final String description;
    protected boolean isDone = false;

    /**
     * Initialises the task with the given description
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * @param taskString description of the ToDo
     * @return a new ToDo task
     * @throws CreateToDoException if taskString is null or empty
     */
    public static ToDo createToDo(String taskString) throws CreateToDoException {
        if (taskString == null || taskString.isBlank()) {
            throw new CreateToDoException("soo what exactly are you trying to do? :)");
        }
        return new ToDo(taskString);
    }

    /**
     * @param taskString description of the Deadline
     * @return a new Deadline task
     * @throws CreateDeadlineException if deadline input arguments are invalid
     */
    public static Deadline createDeadline(String taskString) throws CreateDeadlineException {
        if (taskString == null || taskString.isBlank()) {
            throw new CreateDeadlineException("soo what exactly are you trying to do? :)");
        }
        if (!taskString.contains("/by")) {
            throw new CreateDeadlineException(
                    "You can't expect me to track a deadline if you don't give me the.. deadline right? haha\n"
                            + "please include '/by' followed by the deadline :)");
        }
        String[] descriptionNBy = taskString.split("/by");
        if (descriptionNBy.length < 2 || descriptionNBy[0].isBlank()) {
            throw new CreateDeadlineException("You can't expect me to track a deadline with no name! :)");
        }
        if (descriptionNBy.length > 2) {
            throw new CreateDeadlineException("Don't procrastinate with multiple deadlines !! :)");
        }
        assert descriptionNBy.length == 2 : "descriptionNBy should have 2 elements: description, by.";

        return new Deadline(descriptionNBy[0].trim(),
                descriptionNBy[1].trim());
    }

    /**
     * @param taskString description of the Event
     * @return a new Event task
     * @throws CreateEventException if event input arguments are invalid
     */
    public static Event createEvent(String taskString) throws CreateEventException {
        if (taskString == null || taskString.isBlank()) {
            throw new CreateEventException("soo what exactly are you trying to do? :)");
        }
        if (!taskString.contains("/from") || !taskString.contains("/to")) {
            throw new CreateEventException(
                    "I'm not sure how you're going for an event without a start time or end time\n"
                            + "please include '/from' and '/to' so I can keep track for you :)");
        }
        if (taskString.indexOf("/from") > taskString.indexOf("/to")) {
            throw new CreateEventException("Sorry! /from must appear before /to :)");
        }
        String[] descriptionNFromNTo = taskString.split("/from |/to ");
        if (descriptionNFromNTo.length < 3 || descriptionNFromNTo[0].isBlank()) {
            throw new CreateEventException("You can't expect me to track an event with no name! :)");
        }
        if (descriptionNFromNTo.length > 3) {
            throw new CreateEventException("um.. I'm not sure how I can track for you an event that has\n"
                    + "more than 1 start or end time! :)");
        }
        assert descriptionNFromNTo.length == 3 : "descriptionNFromNTo should have 3 elements: description, from, to.";

        return new Event(descriptionNFromNTo[0].trim(),
                descriptionNFromNTo[1].trim(),
                descriptionNFromNTo[2].trim());
    }

    /**
     * Returns a new task created from the parsedString taken from the saved data file.
     * <p></p>
     * The parsedString is expected to be in the format:
     * <p></p>
     * * ToDo-> T | y or n | description <p></p>
     * * Deadline-> D | y or n | description | by <p></p>
     * * Event-> E | y or n | description | from | to <p></p>
     * @param parsedString string from data file
     * @return a subclass of Task (ToDo, Event, Deadline)
     * @throws CreateTaskException if save file corrupted or edited such that format is not what is expected
     */
    public static Task createFromParsedString(String parsedString) throws CreateTaskException {
        // Get trimmed tokens
        String[] parsedTokens = getTrimmedTokens(parsedString);

        // The check below considers todos having minimum of 3 tokens, and events having maximum of 5 tokens.
        if (parsedTokens.length < 3 || parsedTokens.length > 5) {
            throw new CreateTaskException("Token count not valid. File corrupted.");
        }
        return createFromParsedTokens(parsedTokens);
    }

    /**
     * Returns the tokens from parsed string with whitespaces trimmed.
     *
     * @param parsedString string from data file
     * @return trimmed tokens from parsedString
     */
    private static String[] getTrimmedTokens(String parsedString) {
        String[] parsedTokens = parsedString.split("\\|");
        for (int i = 0; i < parsedTokens.length; i++) {
            parsedTokens[i] = parsedTokens[i].trim();
        }
        return parsedTokens;
    }

    /**
     * Returns a task created from the parsed tokens taken from the saved data file.
     *
     * @param parsedTokens tokens from parsedString
     * @return a subclass of Task (ToDo, Event, Deadline)
     * @throws CreateTaskException if save file corrupted or edited such that format is not what is expected
     */
    private static Task createFromParsedTokens(String[] parsedTokens) throws CreateTaskException {
        assert parsedTokens.length >= 3 : "parsedTokens should have at least 3 elements";

        String taskType = parsedTokens[0];
        boolean isTaskDone = parsedTokens[1].equals("y");
        String taskDescription = parsedTokens[2];

        // Check if taskType is valid, only T, D, E are valid
        if (!"TDE".contains(taskType)) {
            throw new CreateTaskException("taskType invalid. File corrupted.");
        }
        Task newTask;
        if (taskType.equals("T") && parsedTokens.length == 3) {
            newTask = Task.createToDo(taskDescription);
        } else if (taskType.equals("D") && parsedTokens.length == 4) {
            String deadlineToken = parsedTokens[3];
            newTask = Task.createDeadline(taskDescription + String.format("/by %s", deadlineToken));
        } else if (taskType.equals("E") && parsedTokens.length == 5) {
            String fromToken = parsedTokens[3];
            String toToken = parsedTokens[4];
            newTask = Task.createEvent(taskDescription + String.format("/from %s /to %s", fromToken, toToken));
        } else {
            throw new CreateTaskException("Task type and token count not matched. File corrupted.");
        }
        // Finally mark if task is done or not
        if (isTaskDone) {
            assert newTask != null;
            newTask.markDone();
        }
        return newTask;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                isDone ? "X" : " ", this.description);
    }

    public abstract String toParsedFormat();
}

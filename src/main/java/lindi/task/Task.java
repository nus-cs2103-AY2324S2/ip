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
     * Factory method for subclasses of Task.
     *
     * @param fullString full user input string
     * @return ToDo, Deadline or Event depending on input string
     * @throws CreateTaskException if arguments to create task are invalid
     */
    public static Task create(String fullString) throws CreateTaskException {
        // s is either `todo`, `event` or `deadline`
        String[] sTokens = fullString.split(" ", 2);
        if (sTokens.length < 2) {
            throw new CreateTaskException("soo what exactly are you trying to do? :)");
        }
        String command = sTokens[0];
        String taskString = sTokens[1];
        switch (command) {
        case "todo":
            return new ToDo(taskString);
        case "deadline":
            // Further split the remaining string based on /by
            if (!taskString.contains("/by")) {
                throw new CreateDeadlineException(
                        "You can't expect me to track a deadline if you don't give me the.. deadline right? haha\n"
                                + "please include '/by' followed by the deadline :)");
            }
            String[] descriptionNBy = taskString.split("/by");
            if (descriptionNBy.length > 2) {
                throw new CreateDeadlineException("Don't procrastinate with multiple deadlines !! :)");
            }
            assert descriptionNBy.length == 2;
            return new Deadline(descriptionNBy[0].trim(),
                                descriptionNBy[1].trim());
        case "event":
            // Further split the remaining string based on /from and /to
            if (!taskString.contains("/from") || !taskString.contains("/to")) {
                throw new CreateEventException(
                        "I'm not sure how you're going for an event without a start time or end time\n"
                                + "please include '/from' and '/to' so I can keep track for you :)");
            }
            if (taskString.indexOf("/from") > taskString.indexOf("/to")) {
                throw new CreateEventException("Sorry! /from must appear before /to :)");
            }
            String[] descriptionNFromNTo = taskString.split("/from |/to ");

            if (descriptionNFromNTo.length > 3) {
                throw new CreateEventException("um.. I'm not sure how I can track for you an event that has\n"
                        + "more than 1 start or end time! :)");
            }
            assert descriptionNFromNTo.length == 3;
            return new Event(descriptionNFromNTo[0].trim(),
                             descriptionNFromNTo[1].trim(),
                             descriptionNFromNTo[2].trim());
        default:
            return null;
        }
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
        String[] parsedTokens = parsedString.split("\\|");
        for (int i = 0; i < parsedTokens.length; i++) {
            parsedTokens[i] = parsedTokens[i].trim();
        }

        // The check below considers todos having minimum of 3 tokens, and events having maximum of 5 tokens.
        if (parsedTokens.length < 3 || parsedTokens.length > 5) {
            throw new CreateTaskException("Token count not valid. File corrupted.");
        }

        boolean isTaskDone = parsedTokens[1].equals("y");
        String taskDescription = parsedTokens[2];
        String taskType = parsedTokens[0].equals("T") ? "todo"
                : parsedTokens[0].equals("D") ? "deadline"
                : parsedTokens[0].equals("E") ? "event"
                : "unknown";
        if (taskType.equals("unknown")) {
            throw new CreateTaskException("taskType invalid. File corrupted.");
        }

        String commandString = String.format("%s %s", taskType, taskDescription);
        Task newTask;

        // Create the new lindi.task using the user input string constructor
        if (taskType.equals("todo") && parsedTokens.length == 3) {
            newTask = Task.create(commandString);
        } else if (taskType.equals("deadline") && parsedTokens.length == 4) {
            newTask = Task.create(commandString + String.format("/by %s", parsedTokens[3]));
        } else if (taskType.equals("event") && parsedTokens.length == 5) {
            newTask = Task.create(commandString + String.format("/from %s /to %s", parsedTokens[3], parsedTokens[4]));
        } else {
            throw new CreateTaskException("Task type and token count not matched. File corrupted.");
        }

        // Finally mark if lindi.task is done or not
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

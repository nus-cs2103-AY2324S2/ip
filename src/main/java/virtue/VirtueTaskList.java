package virtue;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/** A task list used in this chatbot. */
public class VirtueTaskList {
    private List<VirtueTask> tasks;

    /** Creates a new task list. */
    public VirtueTaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    protected int numTasks() {
        return tasks.size();
    }

    /**
     * Gets the i-th task in the task list, where i is the index input by the user.
     *
     * @param index The index input by the user (starts from 1 and not 0).
     * @return The i-th task in the task list.
     */
    private VirtueTask getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param description The description of the todo task to be added.
     * @return The message to be sent to the user.
     */
    private String addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        String message = "";
        message += "Got it. I've added this task:\n";
        message += "   " + todo + "\n";
        message += "Now you have " + numTasks + " task" + sOrNone + " in the list.";
        return message;
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param description The description of the deadline task to be added.
     * @param by The date/time for the task to be completed.
     * @return The message to be sent to the user.
     */
    private String addDeadline(String description, VirtueDateTime by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        String message = "";
        message += "Got it. I've added this task:\n";
        message += "   " + deadline + "\n";
        message += "Now you have " + numTasks + " task" + sOrNone + " in the list.";
        return message;
    }

    /**
     * Adds an event task to the task list.
     *
     * @param description The description of the event task to be added.
     * @param from The date/time of the start of the event.
     * @param to The date/time of the end of the event.
     * @return The message to be sent to the user.
     */
    private String addEvent(String description, VirtueDateTime from, VirtueDateTime to) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        String message = "";
        message += "Got it. I've added this task:\n";
        message += "   " + event + "\n";
        message += "Now you have " + numTasks + " task" + sOrNone + " in the list.";
        return message;
    }

    /**
     * For each index i input by the user, marks the i-th task in the task list as done.
     *
     * @param indices The indices input by the user (starts from 1 and not 0).
     * @return The message to be sent to the user.
     */
    private String markTasksAsDone(Integer... indices) {
        int numOfIndices = 0;
        String message = "";

        for (int index : indices) {
            VirtueTask task = getTask(index);
            task.markAsDone();
            message += "   " + task + "\n";
            numOfIndices++;
        }

        String singularOrPlural = numOfIndices == 1 ? "this task" : "these tasks";
        message = "Nice! I've marked " + singularOrPlural + " as done:\n" + message;
        return message;
    }

    /**
     * For each index i input by the user, marks the i-th task in the task list as not done.
     *
     * @param indices The indices input by the user (starts from 1 and not 0).
     * @return The message to be sent to the user.
     */
    private String markTasksAsNotDone(Integer... indices) {
        int numOfIndices = 0;
        String message = "";

        for (int index : indices) {
            VirtueTask task = getTask(index);
            task.markAsNotDone();
            message += "   " + task + "\n";
            numOfIndices++;
        }

        String singularOrPlural = numOfIndices == 1 ? "this task" : "these tasks";
        message = "OK, I've marked " + singularOrPlural + " as not done yet:\n" + message;
        return message;
    }

    /**
     * Prints out the task list with all the tasks contained in it.
     *
     * @return The message to be sent to the user, including the task list.
     */
    protected String printOut() {
        int numOfTasks = tasks.size();
        String message = "";
        message += "Here are the tasks in your list:\n";

        for (int index = 1; index <= numOfTasks; index++) {
            message += " " + index + "." + tasks.get(index - 1);

            if (index < numOfTasks) {
                message += "\n";
            }
        }

        return message;
    }

    /**
     * For each index i input by the user, marks the i-th task in the task list
     *
     * @param indices The indices input by the user (starts from 1 and not 0).
     * @return The message to be sent to the user.
     */
    private String deleteTasks(Integer... indices) {
        int numOfIndices = 0;
        String message = "";

        for (int index : indices) {
            VirtueTask deletedTask = tasks.remove(index - 1);
            message = "   " + deletedTask + "\n" + message;
            numOfIndices++;
        }

        String singularOrPlural = numOfIndices == 1 ? "this task" : "these tasks";
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        message = "Noted. I've removed " + singularOrPlural + ":\n" + message;
        message += "Now you have " + numTasks + " task" + sOrNone + " in the list.";
        return message;
    }

    /**
     * Prints the tasks in the list that contains a certain keyword.
     *
     * @param keyword The keyword to filter the list with.
     * @return The message to be sent to the user, including the tasks.
     */
    private String printTasksWithWord(String keyword) {
        int numOfTasks = tasks.size();
        String message = "";
        message += "Here are the matching tasks in your list:\n";

        for (int index = 1; index <= numOfTasks; index++) {
            if (getTask(index).hasKeyword(keyword)) {
                message += " " + index + "." + tasks.get(index - 1);
                if (index < numOfTasks) {
                    message += "\n";
                }
            }
        }

        return message;
    }

    /**
     * Executes the command on this task list and
     * loads the result message into the command.
     *
     * @param command The command to be executed on this task list.
     */
    public void applyCommand(Command command) {
        String message;

        switch (command.type) {
        case LIST:
            message = printOut();
            break;
        case MARK:
            message = markTasksAsDone(command.indices);
            break;
        case UNMARK:
            message = markTasksAsNotDone(command.indices);
            break;
        case DELETE:
            message = deleteTasks(command.indices);
            break;
        case TODO:
            message = addTodo(command.description);
            break;
        case DEADLINE:
            message = addDeadline(command.description, command.by);
            break;
        case EVENT:
            message = addEvent(command.description, command.from, command.to);
            break;
        default: // case FIND:
            message = printTasksWithWord(command.description);
        }

        assert message != null : "message should not be null";

        command.setResultMessage(message);
    }

    /**
     * Adds a task to the task list from a line in the saved file.
     *
     * @param str A specific line from the saved file.
     * @throws VirtueDateTimeException If dates in a deadline/event file are not in the correct format.
     */
    public void addFromFile(String str) throws VirtueDateTimeException {
        String taskType = str.split(" \\| ")[0];
        String description = str.split(" \\| ")[2];
        int marked = Integer.parseInt(str.split(" \\| ")[1]);

        switch (taskType) {
        case "T":
            tasks.add(new Todo(description));
            break;
        case "D":
            try {
                VirtueDateTime dateTime = new VirtueDateTime(str.split(" \\| ")[3]);
                tasks.add(new Deadline(description, dateTime));
                break;
            } catch (DateTimeParseException e) {
                throw new VirtueDateTimeException("by", "deadline");
            }
        default: // case "E":
            VirtueDateTime fromDateTime;
            VirtueDateTime toDateTime;

            try {
                fromDateTime = new VirtueDateTime(str.split(" \\| ")[3]);
            } catch (DateTimeParseException e) {
                throw new VirtueDateTimeException("from", "event");
            }

            try {
                toDateTime = new VirtueDateTime(str.split(" \\| ")[4]);
            } catch (DateTimeParseException e) {
                throw new VirtueDateTimeException("to", "event");
            }

            tasks.add(new Event(description, fromDateTime, toDateTime));
            break;
        }

        if (marked == 1) {
            getTask(numTasks()).markAsDone();
        }
    }

    /**
     * Creates the string to be saved in a file.
     *
     * @return The concatenation of all fileFormat strings of all tasks in the list
     *     with each task separated by a line break.
     */
    public String fileFormat() {
        String str = "";

        if (numTasks() == 0) {
            return str;
        }

        for (int i = 1; i <= numTasks() - 1; i++) {
            str += getTask(i).fileFormat();
            str += System.lineSeparator();
        }

        str += getTask(numTasks()).fileFormat();
        return str;
    }
}

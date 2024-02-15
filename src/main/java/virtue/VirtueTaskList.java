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
     */
    private void addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Got it. I've added this task:");
        Ui.printWithIndent("   " + todo);
        Ui.printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        Ui.printHorizontalLine();
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param description The description of the deadline task to be added.
     * @param by The date/time for the task to be completed.
     */
    private void addDeadline(String description, VirtueDateTime by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Got it. I've added this task:");
        Ui.printWithIndent("   " + deadline);
        Ui.printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        Ui.printHorizontalLine();
    }

    /**
     * Adds an event task to the task list.
     *
     * @param description The description of the event task to be added.
     * @param from The date/time of the start of the event.
     * @param to The date/time of the end of the event.
     */
    private void addEvent(String description, VirtueDateTime from, VirtueDateTime to) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Got it. I've added this task:");
        Ui.printWithIndent("   " + event);
        Ui.printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        Ui.printHorizontalLine();
    }

    /**
     * Marks the i-th task in the task list as done, where i is the index input by the user.
     *
     * @param index The index input by the user (starts from 1 and not 0).
     */
    private void markTaskAsDone(int index) {
        VirtueTask task = getTask(index);
        task.markAsDone();
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Nice! I've marked this task as done:");
        Ui.printWithIndent("   " + task);
        Ui.printHorizontalLine();
    }

    /**
     * Marks the i-th task in the task list as not done, where i is the index input by the user.
     *
     * @param index The index input by the user (starts from 1 and not 0).
     */
    private void markTaskAsNotDone(int index) {
        VirtueTask task = getTask(index);
        task.markAsNotDone();
        Ui.printHorizontalLine();
        Ui.printWithIndent(" OK, I've marked this task as not done yet:");
        Ui.printWithIndent("   " + task);
        Ui.printHorizontalLine();
    }

    /** Prints out the task list with all the tasks contained in it. */
    protected void printOut() {
        int numOfTasks = tasks.size();
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Here are the tasks in your list:");

        for (int index = 1; index <= numOfTasks; index++) {
            Ui.printWithIndent(" " + index + "." + tasks.get(index - 1));
        }

        Ui.printHorizontalLine();
    }

    /**
     * Deletes the i-th task in the task list, where i is the index input by the user.
     *
     * @param index The index input by the user (starts from 1 and not 0).
     */
    private void deleteTask(int index) {
        VirtueTask deletedTask = tasks.remove(index - 1);
        int numTasks = numTasks();
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Noted. I've removed this task:");
        Ui.printWithIndent("   " + deletedTask);
        Ui.printWithIndent(" Now you have " + numTasks + " tasks in the list.");
        Ui.printHorizontalLine();
    }

    /**
     * Prints the tasks in the list that contains a certain keyword.
     *
     * @param keyword The keyword to filter the list with.
     */
    private void printTasksWithWord(String keyword) {
        int numOfTasks = tasks.size();
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Here are the matching tasks in your list:");

        for (int index = 1; index <= numOfTasks; index++) {
            if (getTask(index).hasKeyword(keyword)) {
                Ui.printWithIndent(" " + index + "." + tasks.get(index - 1));
            }
        }

        Ui.printHorizontalLine();
    }

    /**
     * Executes the command on this task list.
     *
     * @param command The command to be executed on this task list.
     */
    public void executeCommand(Command command) {
        switch (command.type) {
        case LIST:
            printOut();
            break;
        case MARK:
            markTaskAsDone(command.index);
            break;
        case UNMARK:
            markTaskAsNotDone(command.index);
            break;
        case DELETE:
            deleteTask(command.index);
            break;
        case TODO:
            addTodo(command.description);
            break;
        case DEADLINE:
            addDeadline(command.description, command.by);
            break;
        case EVENT:
            addEvent(command.description, command.from, command.to);
        case FIND:
            printTasksWithWord(command.description);
        }
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
        case "E":
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
     * @return The concatenation of all fileFormat strings of all tasks in the list,
     * with each task separated by a line break.
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

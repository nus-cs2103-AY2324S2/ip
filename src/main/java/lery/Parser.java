package lery;
import lery.task.Deadline;
import lery.task.Event;
import lery.task.Task;
import lery.task.TaskList;
import lery.task.Todo;


/**
 * Represents a Parser.
 * A Parser handles the parsing of user commands in the Lery chatbot application.
 *
 */
public class Parser {
    private Storage storage;

    /**
     * Constructs a Parser object with the specified Storage.
     *
     * @param s the Storage object used for managing tasks.
     */
    public Parser(Storage s) {
        this.storage = s;
    }


    /**
     * Parses the given command and executes the corresponding action.
     *
     * @param command the user command to be parsed.
     * @return a string containing the result or feedback of the command execution.
     * @throws LeryException if the user command has an error.
     */
    public String parseCommand(String command) throws LeryException {
        assert storage != null : "Storage should not be null";
        assert command != null : "Command should not be null";
        TaskList taskList = this.storage.getTaskList();
        if (command.equalsIgnoreCase("list")) {
            return "Woof! " + taskList.printList();
        } else if (command.startsWith("mark")) {
            return handleMarkAndUnmarkCommand(command, taskList, true);
        } else if (command.startsWith("unmark")) {
            return handleMarkAndUnmarkCommand(command, taskList, false);
        } else if (command.startsWith("delete")) {
            return handleDeleteCommand(command, taskList);
        } else if (command.startsWith("find")) {
            String d = command.substring(5);
            return this.parseFindTaskCommand(d);
        } else if (command.startsWith("sort")) {
            this.storage.sortTask();
            this.storage.saveTasks(taskList);
            return "\nWoof! List is sorted by their deadline and task type.\n";
        } else {
            return this.parseAddTaskCommand(command);
        }
    }

    /**
     * Handles the marking and unmarking commands for a specific task in the task list.
     *
     * @param command       The user command to mark or unmark a task.
     * @param taskList      The task list to operate on.
     * @param isMarkCommand True if the command is a mark command, false if it's an unmark command.
     * @return A string containing the result or feedback of the command execution.
     * @throws LeryException If the user command has an error.
     */
    private String handleMarkAndUnmarkCommand(String command, TaskList taskList,
                                              boolean isMarkCommand) throws LeryException {
        try {
            Integer id;
            Task t;
            if (isMarkCommand) {
                id = Integer.parseInt(command.substring(5));
                t = taskList.getTask(id - 1);
                t.markAsDone();
            } else {
                id = Integer.parseInt(command.substring(7));
                t = taskList.getTask(id - 1);
                t.unmarkAsDone();
            }

            this.storage.saveTasks(taskList);
            String msg = t.getIsDone()
                    ? "Woof! I've marked this task as done:\n"
                    : "Woof! I've marked this task as not done yet:\n";
            return msg + "[" + t.getStatusIcon() + "] " + t.getDescription();
        } catch (NumberFormatException e) {
            throw new LeryException("Woof! Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new LeryException("Woof! Please enter a task number.");
        }
    }

    /**
     * Handles the deletion command for a specific task in the task list.
     *
     * @param command  The user command to delete a task.
     * @param taskList The task list to operate on.
     * @return A string containing the result or feedback of the command execution.
     * @throws LeryException If the user command has an error.
     */
    private String handleDeleteCommand(String command, TaskList taskList) throws LeryException {
        try {
            Integer id = Integer.parseInt(command.substring(7));
            return parseDeleteTaskCommand(taskList.getTask(id - 1));
        } catch (NumberFormatException e) {
            throw new LeryException("Woof! Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new LeryException("Woof! Please enter a task number.");
        }
    }

    /**
     * Parses the command and adds a new task to the task list.
     *
     * @param command the user command to add a new task.
     * @return a string containing the result or feedback of the command execution.
     * @throws LeryException if the user command has an error.
     */
    public String parseAddTaskCommand(String command) throws LeryException {
        assert command != null : "Command should not be null";
        String msg = "Woof! Got it. I've added this task:\n";
        Task newTask;
        if (command.startsWith("todo")) {
            newTask = createTodoTask(command);
        } else if (command.startsWith("deadline")) {
            newTask = createDeadlineTask(command);
        } else if (command.startsWith("event")) {
            newTask = createEventTask(command);
        } else {
            throw new LeryException("Woof! Please provide a valid command.");
        }
        TaskList tl = this.storage.getTaskList();
        tl.add(newTask);
        this.storage.saveTasks(tl);
        msg = msg + "[" + newTask.getType() + "]" + "[" + newTask.getStatusIcon()
                + "]" + " " + newTask.getDescription() + newTask.getExtraInfo()
                + "\nNow you have " + this.storage.getSize()
                + " tasks in the list.";
        return msg;
    }

    /**
     * Creates a new Todo task based on the user command.
     *
     * @param command The user command for creating a Todo task.
     * @return A new Todo task created from the user command.
     * @throws LeryException If there is an error creating the Todo task.
     */
    private Todo createTodoTask(String command) throws LeryException {
        try {
            return new Todo(command.substring(5), false);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new LeryException("Woof! Please provide event name.");
        }
    }

    /**
     * Creates a new Deadline task based on the user command.
     *
     * @param command The user command for creating a Deadline task.
     * @return A new Deadline task created from the user command.
     * @throws LeryException If there is an error creating the Deadline task.
     */
    private Deadline createDeadlineTask(String command) throws LeryException {
        try {
            String[] taskDesc = command.substring(9).split("/by ");
            this.storage.checkDateFormat(taskDesc[1]);
            return new Deadline(taskDesc[0], taskDesc[1], false);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new LeryException("Woof! Please provide event details.");
        }
    }

    /**
     * Creates a new Event task based on the user command.
     *
     * @param command The user command for creating an Event task.
     * @return A new Event task created from the user command.
     * @throws LeryException If there is an error creating the Event task.
     */
    private Event createEventTask(String command) throws LeryException {
        try {
            return new Event(command.substring(6), false);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new LeryException("Woof! Please provide event details.");
        }
    }

    /**
     * Parses the command and deletes the specified task from the task list.
     *
     * @param task the task to be deleted.
     * @return a string containing the result or feedback of the command execution.
     * @throws LeryException If there is an error during the deletion process.
     */
    public String parseDeleteTaskCommand(Task task) throws LeryException {
        assert task != null : "Task to delete must not be null";
        TaskList tl = this.storage.deleteTask(task);
        this.storage.saveTasks(tl);
        String msg = "Woof! I've removed this task:\n" + task.getType() + "["
                + task.getStatusIcon() + "]" + " " + task.getDescription()
                + task.getExtraInfo() + "\nNow you have "
                + this.storage.getSize() + " tasks in the list.\n";
        return msg;
    }
    /**
     * Parses the command and finds the specified task from the task list.
     *
     * @param d the command to be parsed.
     * @return a string containing the result or feedback of the command execution.
     */
    public String parseFindTaskCommand(String d) {
        assert d != null : "Command should not be null";
        TaskList t = this.storage.getTaskList();
        TaskList findList = new TaskList();
        for (int i = 0; i < t.getSize(); i++) {
            Task curr = t.getTask(i);
            String description = curr.getDescription();
            if (description.contains(d)) {
                findList.add(curr);
            }
        }
        if (findList.getSize() == 0) {
            return "Woof! Sorry there are no tasks that fits your keyword.";
        }
        return "Woof! " + findList.printList();
    }
}

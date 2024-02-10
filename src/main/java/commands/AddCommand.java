package commands;

import exceptions.InvalidInputFormatException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import utils.TaskList;
import utils.Ui;

/**
 * The AddCommand class represents a command to add tasks to the task list.
 */
public class AddCommand extends Command {
    private final CommandType commandType;
    private final String[] input;

    /**
     * Constructs an AddCommand object with the specified command type and input.
     *
     * @param commandType The type of command (TODO, DEADLINE, EVENT).
     * @param input       The input containing task details.
     */
    public AddCommand(CommandType commandType, String[] input) {
        this.commandType = commandType;
        this.input = input;
    }

    /**
     * Executes the add command based on the command type.
     *
     * @param ui                            The user interface object.
     * @param taskList                      The task list object.
     * @throws InvalidInputFormatException  If the input format is invalid.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) throws InvalidInputFormatException {
        switch (commandType) {
        case TODO:
            addTodo(input[1], taskList, ui);
            break;
        case DEADLINE:
            addDeadline(input[1].split("/", 2), taskList, ui);
            break;
        case EVENT:
            addEvent(input[1].split("/", 2), taskList, ui);
            break;
        default:
            break;
        }
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param todoDetails                   The description of the todo task.
     * @param taskList                      The task list object.
     * @param ui                            The user interface object.
     * @throws InvalidInputFormatException  If the input format is invalid.
     */
    private void addTodo(String todoDetails, TaskList taskList, Ui ui) throws InvalidInputFormatException {
        if (todoDetails.trim().isEmpty()) {
            throw new InvalidInputFormatException("todo");
        }
        Task newTask = new Todo(todoDetails);
        taskList.addTask(newTask);
        ui.displayAddedTask(newTask, taskList.getLength());
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param deadlineDetails               The details of the deadline task.
     * @param taskList                      The task list object.
     * @param ui                            The user interface object.
     * @throws InvalidInputFormatException  If the input format is invalid.
     */
    private void addDeadline(String[] deadlineDetails, TaskList taskList, Ui ui) throws InvalidInputFormatException {
        if (deadlineDetails.length < 2 || deadlineDetails[1].split(" ", 2).length < 2) {
            throw new InvalidInputFormatException("deadline");
        }
        String deadlineDescription = deadlineDetails[0];
        String deadline = deadlineDetails[1].split(" ", 2)[1];
        Task newTask = new Deadline(deadlineDescription, deadline);
        taskList.addTask(newTask);
        ui.displayAddedTask(newTask, taskList.getLength());
    }

    /**
     * Adds an event task to the task list.
     *
     * @param eventDetails                  The details of the event task.
     * @param taskList                      The task list object.
     * @param ui                            The user interface object.
     * @throws InvalidInputFormatException  If the input format is invalid.
     */
    private void addEvent(String[] eventDetails, TaskList taskList, Ui ui) throws InvalidInputFormatException {
        if (eventDetails.length < 2) {
            throw new InvalidInputFormatException("event");
        }
        String eventDescription = eventDetails[0];
        String[] fromToDetails = eventDetails[1].split("/", 2);
        if (fromToDetails.length < 2
                || fromToDetails[0].split(" ", 2).length < 2
                || fromToDetails[1].split(" ", 2).length < 2) {
            throw new InvalidInputFormatException("event");
        }
        String from = fromToDetails[0].split(" ", 2)[1];
        String to = fromToDetails[1].split(" ", 2)[1];
        Task newTask = new Event(eventDescription, from, to);
        taskList.addTask(newTask);
        ui.displayAddedTask(newTask, taskList.getLength());
    }
}

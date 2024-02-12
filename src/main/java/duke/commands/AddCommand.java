package duke.commands;

import java.time.LocalDate;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * AddCommand class represents a command to add a new task to the task list.
 * It extends the Command class and provides methods to execute the command.
 */
public class AddCommand extends Command {
    private String taskType;
    private String taskDescription;
    private LocalDate deadline;
    private LocalDate eventStart;
    private LocalDate eventEnd;

    /**
     * Constructs an AddCommand object for adding a todo task.
     *
     * @param taskType        The type of task to add (todo).
     * @param taskDescription The description of the todo task.
     */
    public AddCommand(String taskType, String taskDescription) {
        super();
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    /**
     * Constructs an AddCommand object for adding a deadline task.
     *
     * @param taskType        The type of task to add (deadline).
     * @param taskDescription The description of the deadline task.
     * @param deadline        The deadline of the deadline task.
     */
    public AddCommand(String taskType, String taskDescription, LocalDate deadline) {
        super();
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }

    /**
     * Constructs an AddCommand object for adding an event task.
     *
     * @param taskType        The type of task to add (event).
     * @param taskDescription The description of the event task.
     * @param eventStart      The start date of the event task.
     * @param eventEnd        The end date of the event task.
     */
    public AddCommand(String taskType, String taskDescription, LocalDate eventStart, LocalDate eventEnd) {
        super();
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    /**
     * Executes the add command by creating the corresponding task and adding it to the task list.
     *
     * @param taskList The list of tasks.
     * @param storage  The storage handler.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        Task task = null;
        if (this.taskType.equals("todo")) {
            task = new Todo(this.taskDescription);
        } else if (this.taskType.equals("deadline")) {
            task = new Deadline(this.taskDescription, this.deadline);
        } else {
            task = new Event(this.taskDescription, this.eventStart, this.eventEnd);
        }
        taskList.add(task);

        String consoleText = "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + String.format("Now you have %d tasks in the list.%n", taskList.size());

        return consoleText;
    }
}

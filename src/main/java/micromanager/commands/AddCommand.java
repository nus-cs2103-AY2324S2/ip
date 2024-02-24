package micromanager.commands;

import java.time.LocalDate;

import micromanager.Launcher;
import micromanager.storage.Storage;
import micromanager.storage.TaskList;
import micromanager.tasks.Deadline;
import micromanager.tasks.Event;
import micromanager.tasks.Task;

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
        assert taskType.equals("todo") : "Task should be a todo";
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
        assert taskType.equals("deadline") : "Task should be a deadline";
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
        assert taskType.equals("event") : "Task should be an event";
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
        Task task = createTask();
        taskList.add(task);

        return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + String.format("Now you have %d tasks in the list.%n", taskList.size());
    }

    /**
     * Creates the corresponding task based on the task type.
     *
     * @return The created task.
     */
    public Task createTask() {
        Task task = null;
        if (taskType.equals("todo")) {
            task = new Launcher.Todo(taskDescription);
        } else if (taskType.equals("deadline")) {
            task = new Deadline(taskDescription, deadline);
        } else {
            task = new Event(taskDescription, eventStart, eventEnd);
        }
        return task;
    }
}

package duke;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command {
    public abstract void execute(UI ui) throws DukeException;
}

class ByeCommand extends Command {
    static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand() {
    }

    @Override
    public void execute(UI ui) {
        ui.printMessage(BYE_MESSAGE);
        ui.setActive(false);
    }
}

class EchoCommand extends Command {
    private String message;

    public EchoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(UI ui) {
        ui.printMessage(message);
    }
}

abstract class AddTaskCommand extends Command {
    private static final String EMPTY_DESCRIPTION_MESSAGE =
            "OOPS!!! The description of a todo cannot be empty.";

    protected String description;
    private TaskList taskList;

    public AddTaskCommand(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    protected abstract Task createTask() throws DukeException;

    @Override
    public void execute(UI ui) throws DukeException {
        if (this.description.equals("")) {
            throw new DukeException(EMPTY_DESCRIPTION_MESSAGE);
        }
        Task task;
        try {
            task = this.createTask();
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        this.taskList.addTask(task);
        ArrayList<String> messages = new ArrayList<String>(Arrays.asList(
                "Got it. I've added this task:", "  " + task.toString(),
                String.format("Now you have %d tasks in the list.",
                        this.taskList.getSize())));
        ui.printMessages(messages);
    }
}

class AddTodoCommand extends AddTaskCommand {
    public AddTodoCommand(String description, TaskList taskList) {
        super(description, taskList);
    }

    @Override
    protected Task createTask() {
        return new Todo(this.description);
    }
}

class AddDeadlineCommand extends AddTaskCommand {
    private String byDate;

    public AddDeadlineCommand(String description, String byDate,
            TaskList taskList) {
        super(description, taskList);
        this.byDate = byDate;
    }

    @Override
    protected Task createTask() throws DukeException {
        return new Deadline(this.description, this.byDate);
    }
}

class AddEventCommand extends AddTaskCommand {
    private String fromDate;
    private String toDate;

    public AddEventCommand(String description, String fromDate, String toDate,
            TaskList taskList) {
        super(description, taskList);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    protected Task createTask() throws DukeException {
        return new Event(this.description, this.fromDate, this.toDate);
    }
}

class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(UI ui) {
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList("Here are the tasks in your list:"));
        messages.addAll(this.taskList.listTasks());
        ui.printMessages(messages);
    }
}

class MarkAsDoneCommand extends Command {
    private int index;
    private TaskList taskList;

    public MarkAsDoneCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void execute(UI ui) {
        Task task = this.taskList.getTask(this.index);
        task.markAsDone();
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList("Nice! I've marked this task as done:",
                        "  " + task.toString()));
        ui.printMessages(messages);
    }
}

class UnmarkAsDoneCommand extends Command {
    private int index;
    private TaskList taskList;

    public UnmarkAsDoneCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void execute(UI ui) {
        Task task = this.taskList.getTask(this.index);
        task.unmarkAsDone();
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList("OK, I've marked this task as not done yet:",
                        "  " + task.toString()));
        ui.printMessages(messages);
    }
}

class DeleteTaskCommand extends Command {
    private int index;
    private TaskList taskList;

    public DeleteTaskCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void execute(UI ui) {
        Task task = this.taskList.getTask(index);
        this.taskList.deleteTask(index);
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList("Noted. I've removed this task:", "  "
                        + task.toString(),
                        String.format("Now you have %d tasks in the list.",
                                this.taskList.getSize())));
        ui.printMessages(messages);
    }
}
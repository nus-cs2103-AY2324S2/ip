package echon;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param ui The user interface where the command is executed.
     * @throws EchonException If an error occurs while executing the command.
     */
    public abstract void execute(EchonUi ui) throws EchonException;
}

class ByeCommand extends Command {
    static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand() {
    }

    @Override
    public void execute(EchonUi ui) {
        ui.displayEchonMessage(BYE_MESSAGE);
    }
}

class EchoCommand extends Command {
    private String message;

    public EchoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(EchonUi ui) {
        ui.displayEchonMessage(message);
    }
}

abstract class AddTaskCommand extends Command {
    private static final String EMPTY_DESCRIPTION_MESSAGE =
            "OOPS!!! The description of a todo cannot be empty.";
    private static final String ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_COUNT_MESSAGE = "Now you have %d tasks in the list.";

    protected String description;
    private TaskList taskList;

    public AddTaskCommand(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    protected abstract Task createTask() throws EchonException;

    private ArrayList<String> constructMessages(Task task) {
        ArrayList<String> messages = new ArrayList<String>(Arrays.asList(
                ADDED_MESSAGE, "  " + task.toString(),
                String.format(TASK_COUNT_MESSAGE, this.taskList.getSize())));
        return messages;
    }

    @Override
    public void execute(EchonUi ui) throws EchonException {
        if (this.description.equals("")) {
            throw new EchonException(EMPTY_DESCRIPTION_MESSAGE);
        }
        Task task;
        try {
            task = this.createTask();
        } catch (EchonException e) {
            throw new EchonException(e.getMessage());
        }
        this.taskList.addTask(task);
        assert this.taskList.getSize() > 0 : "taskList should not be empty";
        ui.displayEchonMessages(constructMessages(task));
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
    private String dueDate;

    public AddDeadlineCommand(String description, String dueDate,
            TaskList taskList) {
        super(description, taskList);
        assert dueDate != null : "dueDate should not be null";
        this.dueDate = dueDate;
    }

    @Override
    protected Task createTask() throws EchonException {
        return new Deadline(this.description, this.dueDate);
    }
}

class AddEventCommand extends AddTaskCommand {
    private String fromDate;
    private String toDate;
    private Boolean isTentative;

    public AddEventCommand(String description, String fromDate, String toDate,
            Boolean isTentative, TaskList taskList) {
        super(description, taskList);
        assert fromDate != null : "fromDate should not be null";
        assert toDate != null : "toDate should not be null";
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isTentative = isTentative;
    }

    @Override
    protected Task createTask() throws EchonException {
        if (this.isTentative) {
            return new TentativeEvent(this.description, this.fromDate, this.toDate);
        } else {
            return new Event(this.description, this.fromDate, this.toDate);
        }
    }
}

class ListCommand extends Command {
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";

    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(EchonUi ui) {
        ArrayList<String> messages = new ArrayList<String>(Arrays.asList(LIST_MESSAGE));
        messages.addAll(this.taskList.listTasks());
        ui.displayEchonMessages(messages);
    }
}

class MarkAsDoneCommand extends Command {
    private static final String MARKED_MESSAGE = "Nice! I've marked this task as done:";

    private int index;
    private TaskList taskList;

    public MarkAsDoneCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void execute(EchonUi ui) throws EchonException {
        Task task = this.taskList.getTask(this.index);
        task.markAsDone();
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList(MARKED_MESSAGE, "  " + task.toString()));
        ui.displayEchonMessages(messages);
    }
}

class UnmarkAsDoneCommand extends Command {
    private static final String UNMARKED_MESSAGE = "OK, I've marked this task as not done yet:";

    private int index;
    private TaskList taskList;

    public UnmarkAsDoneCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void execute(EchonUi ui) throws EchonException {
        Task task = this.taskList.getTask(this.index);
        task.unmarkAsDone();
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList(UNMARKED_MESSAGE, "  " + task.toString()));
        ui.displayEchonMessages(messages);
    }
}

class DeleteTaskCommand extends Command {
    private static final String DELETED_MESSAGE = "Noted. I've removed this task:";
    private static final String TASK_COUNT_MESSAGE = "Now you have %d tasks in the list.";

    private int index;
    private TaskList taskList;

    public DeleteTaskCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void execute(EchonUi ui) throws EchonException {
        Task task = this.taskList.getTask(index);
        this.taskList.deleteTask(index);
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList(DELETED_MESSAGE, "  " + task.toString(),
                        String.format(TASK_COUNT_MESSAGE, this.taskList.getSize())));
        ui.displayEchonMessages(messages);
    }
}

class FindTaskCommand extends Command {
    private static final String FOUND_MESSAGE = "Here are the matching tasks in your list:";

    private String keyword;
    private TaskList taskList;

    public FindTaskCommand(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    @Override
    public void execute(EchonUi ui) {
        ArrayList<String> messages = new ArrayList<String>(Arrays.asList(FOUND_MESSAGE));
        messages.addAll(this.taskList.findTasks(this.keyword));
        ui.displayEchonMessages(messages);
    }
}

class AddTentativeSlotCommand extends Command {
    private static final String ADDED_MESSAGE = "Got it. I've added this tentative slot to the event:";

    private int index;
    private String fromDate;
    private String toDate;
    private TaskList taskList;

    public AddTentativeSlotCommand(int index, String fromDate, String toDate, TaskList taskList) {
        this.index = index;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.taskList = taskList;
    }

    @Override
    public void execute(EchonUi ui) throws EchonException {
        Task task = this.taskList.getTask(this.index);
        if (!(task instanceof TentativeEvent)) {
            throw new EchonException("This task is not a tentative event");
        }
        TentativeEvent event = (TentativeEvent) task;
        event.addTentativeSlot(this.fromDate, this.toDate);
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList(ADDED_MESSAGE, "  " + event.toString()));
        ui.displayEchonMessages(messages);
    }
}

class ConfirmTentativeSlotCommand extends Command {
    private static final String DECIDED_MESSAGE =
            "Got it. I've confirmed this slot for the event:";

    private int index;
    private int slotIndex;
    private TaskList taskList;

    public ConfirmTentativeSlotCommand(int index, int slotIndex, TaskList taskList) {
        this.index = index;
        this.slotIndex = slotIndex;
        this.taskList = taskList;
    }

    @Override
    public void execute(EchonUi ui) throws EchonException {
        Task task = this.taskList.getTask(this.index);
        if (!(task instanceof TentativeEvent)) {
            throw new EchonException("This task is not a tentative event");
        }
        TentativeEvent event = (TentativeEvent) task;
        Event confirmedEvent = event.createEvent(this.slotIndex);
        this.taskList.changeTask(this.index, confirmedEvent);
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList(DECIDED_MESSAGE, "  " + confirmedEvent.toString()));
        ui.displayEchonMessages(messages);
    }
}

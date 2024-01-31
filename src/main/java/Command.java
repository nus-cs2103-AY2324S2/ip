import java.time.LocalDate;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}

class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markAsDone(taskNumber);
        ui.showMarked(tasks.getTask(taskNumber));
    }
}

class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.unmarkAsDone(taskNumber);
        ui.showUnmarked(tasks.getTask(taskNumber));
    }
}

class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showHelpCommand();
    }

}

class TodoCommand extends Command {
    private final String todo;

    public TodoCommand(String command) {
        this.todo = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task curr = new Todo(todo);
        tasks.add(curr);
        ui.showTask(curr, tasks.size());
    }
}

class DeadlineCommand extends Command {

    private final String task;

    private final LocalDate by;

    public DeadlineCommand(String task, LocalDate by) {
        this.task = task;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task curr = new Deadline(task, by);
        tasks.add(curr);
        ui.showTask(curr, tasks.size());
    }
}

class EventCommand extends Command {
    private final String task;

    private final LocalDate from;
    private final LocalDate to;

    public EventCommand(String task, LocalDate from, LocalDate to) {
        this.task = task;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task curr = new Event(task, from, to);
        tasks.add(curr);
        ui.showTask(curr, tasks.size());
    }
}

class DeleteCommand extends Command {

    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(id);
        tasks.remove(id);
        ui.removeTask(task, tasks.size());
    }
}

class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

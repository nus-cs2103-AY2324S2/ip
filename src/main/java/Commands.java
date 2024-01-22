class AddCommand extends Command {
    private String task;

    public AddCommand(String task) throws InvalidCommandException {
        task = task.trim();
        if (task.length() == 0) {
            throw new InvalidCommandException("The description of a task cannot be empty.");
        }
        this.task = task;
    }

    public void execute(Storage storage) throws DukeException {
        storage.addTask(new Task(task));
        System.out.println("Added: " + task);
    }
}

class ListCommand extends Command {
    public void execute(Storage storage) throws DukeException {
        storage.listTasks();
    }
}

abstract class CommandTakingTaskIndex extends Command {
    /**
     * The 0-indexed index of the task to be operated on.
     */
    protected int index;

    /**
     * @param index 1-based index of the task.
     * @throws InvalidCommandException if the index is invalid.
     */
    public CommandTakingTaskIndex(String index) throws InvalidCommandException {
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("The task number must be an integer.");
        }
        if (this.index <= 0) {
            throw new InvalidCommandException("The task number must be positive.");
        }
        this.index--;
    }
}

class MarkDoneCommand extends CommandTakingTaskIndex {
    public MarkDoneCommand(String index) throws InvalidCommandException {
        super(index);
    }

    public void execute(Storage storage) throws DukeException {
        storage.markTaskAsDone(index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(storage.getTaskDescription(index));
    }
}

class UnmarkDoneCommand extends CommandTakingTaskIndex {
    public UnmarkDoneCommand(String index) throws InvalidCommandException {
        super(index);
    }

    public void execute(Storage storage) throws DukeException {
        storage.unmarkTaskAsDone(index);
        System.out.println("OK, I've unmarked this task as not done yet:");
        System.out.println(storage.getTaskDescription(index));
    }
}

public class Commands {
    public static void registerCommands() {
        Parser.registerCommand("add", s -> new AddCommand(s));
        Parser.registerCommand("list", s -> new ListCommand());
        Parser.registerCommand("bye", s -> new ByeCommand());
        Parser.registerCommand("mark", s -> new MarkDoneCommand(s));
        Parser.registerCommand("unmark", s -> new UnmarkDoneCommand(s));
    }
}

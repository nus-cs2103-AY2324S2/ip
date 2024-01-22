class AddCommand extends Command {
    private String task;

    public AddCommand(String task) throws InvalidCommandException {
        task = task.trim();
        if (task.length() == 0) {
            throw new InvalidCommandException("The description of a task cannot be empty.");
        }
        this.task = task;
    }

    public void execute(Storage storage) {
        storage.addTask(task);
        System.out.println("Added: " + task);
    }
}

class ListCommand extends Command {
    public void execute(Storage storage) {
        storage.listTasks();
    }
}

public class Commands {
    public static void registerCommands() {
        Parser.registerCommand("add", s -> new AddCommand(s));
        Parser.registerCommand("list", s -> new ListCommand());
        Parser.registerCommand("bye", s -> new ByeCommand());
    }
}

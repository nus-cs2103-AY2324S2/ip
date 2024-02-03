package commands;

public class MarkCommand extends Command {
    private boolean isUnmarkCommand;

    public MarkCommand(boolean isUnmark) {
        super();
        this.isUnmarkCommand = isUnmark;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            markTask(split, list);
        } catch (InvalidCommandException e) {
            System.out.println("    ____________________________________________________________");
            System.out.printf("      %s\n", e.getMessage());
            System.out.println("    ____________________________________________________________");
        }
    }
}

import java.util.ArrayList;

public class Command {
    private String command;
    private String[] arguments;
    private boolean isExit = false;

    public Command(String cmd, String[] args) {
        this.command = cmd;
        this.arguments = args;
    }

    public void execute(TaskList tl, Ui ui, Storage st) throws InvalidCmdException, InvalidTaskException {
        if (this.command.equals("bye")) {
            ui.echo("Bye. Hope to see you again soon!");
            this.isExit = true;
        } else if (this.command.equals("list")) {
            tl.list(ui);
        } else if (this.command.equals("unmark")) {
            tl.unmark(ui, st, this.arguments);
        } else if (this.command.equals("mark")) {
            tl.mark(ui, st, this.arguments);
        } else if (this.command.equals("delete")) {
            tl.deleteTask(ui, st, this.arguments);
        } else {
            tl.addTask(ui, st, this.command, this.arguments);
        }
    }

    public boolean isExit() {
        return this.isExit;
    }
}

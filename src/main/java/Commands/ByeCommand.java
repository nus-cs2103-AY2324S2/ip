package Commands;

public class ByeCommand extends Command {

    public ByeCommand() {
        super("bye", "bye");
    }

    public String execute() {
        return "Bye. Hope to see you again soon!";
    }
}

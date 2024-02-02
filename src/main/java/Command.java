import java.util.ArrayList;

public abstract class Command {

    public static final ArrayList<String> COMMANDS = new ArrayList<>() {
        {
            add("todo");
            add("deadline");
            add("event");
            add("list");
            add("mark");
            add("unmark");
            add("bye");
            add("delete");
        }
    };

    public String commandType;

    public Command(String commandType) {
        this.commandType = commandType;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BondException;

}

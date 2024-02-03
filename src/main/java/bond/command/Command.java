package bond.command;

import java.util.ArrayList;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.TaskList;

public abstract class Command {

    public static final ArrayList<String> Commands = new ArrayList<>() {
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
    public boolean isExit;

    public Command(String commandType) {
        this.commandType = commandType;
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BondException;

    public boolean isExit() {
        return this.isExit;
    }

}

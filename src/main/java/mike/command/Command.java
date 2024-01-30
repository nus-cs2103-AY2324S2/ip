package mike.command;

import mike.MikeException;
import mike.TaskList;

public abstract class Command {
    String text;

    public Command(String text) {
        this.text = text;
    }

    public abstract void execute(TaskList taskList) throws MikeException;

    public abstract boolean isExit();

    @Override
    public String toString() {
        return "";
    }

}

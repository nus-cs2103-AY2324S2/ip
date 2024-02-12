package duke.history;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import duke.command.Command;
import duke.tasks.Task;


/**
 * placeholder
 */
public class State implements Serializable {
    protected static final State EMPTY_STATE = new State(Command.UNDO, new ArrayList<>());
    private static final long serialVersionUID = 5L;
    private final Command command;
    private final ArrayList<Task> taskList;

    /**
     * @param cmd placeholder
     * @param list placeholder
     */
    public State(Command cmd, ArrayList<Task> list) {
        command = cmd;
        taskList = list;
    }
    private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
        out.defaultWriteObject();
    }
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    public Command getCommand() {
        return command;
    }
    public boolean isIgnoredHistory() {
        return command.isIgnoredHistory();
    }
    @Override
    public String toString() {
        return String.format("%s, %s", command, taskList);
    }
}

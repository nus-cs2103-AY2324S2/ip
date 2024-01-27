package command;

import storage.Storage;
import tasklist.TaskList;

public abstract class Command {
    public enum Types {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND;
    }

    public abstract void execute(TaskList tasks, Storage storage);

    public abstract String getTestData();
}

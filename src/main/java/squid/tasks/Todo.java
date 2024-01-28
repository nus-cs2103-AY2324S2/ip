package squid.tasks;

import squid.constants.REGEX;

import java.util.Objects;

public class Todo extends Task {
    /**
     * The constructor of an Event.
     * @param taskName Name of task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * @return The representation of a Todo.
     */
    @Override
    public String getType() {
        return "[T]";
    }

    /**
     * @return No additional info is needed for a Todo.
     */
    @Override
    public String getAdditionalInfo() {
        return "";
    }

    /**
     * @return The string representation of a Todo task.
     */
    @Override
    public String toString() {
        return String.format("%s[%s]: %s", getType(), completedIcon(), taskName);
    }

    /**
     * @return The string representation of a Todo task to be stored into hard disk.
     */
    @Override
    public String parseStr() {
        return String.format("%s%s%s%s%s\n",
                getType(),
                REGEX.TASK_SPLIT,
                Objects.equals(completedIcon(), "X") ? "X" : "-",
                REGEX.TASK_SPLIT,
                taskName);
    }
}

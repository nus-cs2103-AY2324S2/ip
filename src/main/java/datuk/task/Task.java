package datuk.task;

/**
 * An interface to get descriptions and status of task, and set task.
 * Used in Todo, Event and Deadline.
 */

public interface Task {

    String getDesc();

    String getCheck();

    void setCheck(boolean x);

    String toSave();

    String getType();
}

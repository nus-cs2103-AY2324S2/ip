package duke.task;

/**
 * Class for all TaskList based errors encountered.
 *
 * @author KohGuanZeh
 */
public class TaskListException extends Exception {
    public TaskListException(String errorMessage) {
        super(errorMessage);
    }
}

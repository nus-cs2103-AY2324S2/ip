package hanxiao.exception;

import hanxiao.task.Task;

/**
 * Duplicate Task Exception
 */
public class DuplicateTaskException extends HanxiaoException {
    private Task task;

    /**
     * Constructor
     *
     * @param task duplicated task
     */
    public DuplicateTaskException(Task task) {
        super();
        this.task = task;
    }

    /**
     * Print error message.
     *
     * @return error message
     */
    @Override
    public String getMessage() {
        return String.format("%s the task\n    %s\nis duplicated.", super.getMessage(), this.task);
    }
}

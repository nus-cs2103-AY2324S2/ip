package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.Task;
import earl.tasks.TaskType;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Abstract class representing the adding of a task.
 */
public abstract class TaskHandler extends Handler {

    protected final String[] successMessageFormats = {
            "A new %s %s",
            "hath been appended to the roster of responsibilities.",
            "The ledger of tasks bears witness to %d endeavour(s)."};

    /** Class constructor. */
    public TaskHandler(String args) {
        super(args);
    }

    protected String[] addTask(TaskList tasks, TaskType type, String... data)
            throws EarlException {
        try {
            Task newTask = type.createTask(data);
            tasks.add(newTask);

            return new String[]{
                    String.format(successMessageFormats[0],
                            type.name().toLowerCase(), newTask.toString()),
                    successMessageFormats[1],
                    String.format(successMessageFormats[2], tasks.getSize())};
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException("An error befalls. Example use:");
        } catch (Exception e) {
            throw new EarlException("Command hath faltered: "
                    + "obscure employment of "
                    + type.name().toLowerCase() + ". Example use:");
        }
    }
}

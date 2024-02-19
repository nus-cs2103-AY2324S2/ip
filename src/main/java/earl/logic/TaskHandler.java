package earl.logic;

import earl.exceptions.EarlException;
import earl.exceptions.TimeException;
import earl.tasks.Task;
import earl.tasks.TaskType;
import earl.util.TaskList;

/**
 * Abstract class representing the adding of a task.
 */
public abstract class TaskHandler extends Handler {

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
                "A new " + type.name() + " " + newTask.toString(),
                "hath been appended to the roster of responsibilities.",
                "The ledger of tasks bears witness to "
                        + tasks.getSize() + " endeavour(s)."
            };
        } catch (IndexOutOfBoundsException e) {
            throw new EarlException("An error befalls.");
        } catch (TimeException e) {
            throw new EarlException("An event must commence before "
                    + "it concludes.");
        } catch (Exception e) {
            throw new EarlException("Incomprehensible employment of "
                    + type.name().toLowerCase());
        }
    }
}

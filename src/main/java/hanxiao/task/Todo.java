package hanxiao.task;

import java.time.LocalDate;
import java.util.ArrayList;

import hanxiao.exception.WrongUsageException;


/**
 * Class for task start with todo
 */
public class Todo extends Task {

    /**
     * Constructor
     *
     * @param descrip the dicription of the task.
     */
    public Todo(String descrip) {
        super(descrip);
    }

    /**
     * Constructor
     *
     * @param descrip description.
     * @param tags tags.
     */
    public Todo(String descrip, ArrayList<String> tags) {
        super(descrip, tags);
    }

    /**
     * Override the abstract class
     *
     * @return T.
     */
    @Override
    public String getTaskTypeIcon() {
        return "T";
    }

    /**
     * Override the abstract class
     *
     * @return todo.
     */
    @Override
    public String getTaskType() {
        return "todo";
    }

    /**
     * Whether we have to start doing it
     *
     * @param current current time.
     * @return yes/no.
     */
    @Override
    public boolean isTimeForStart(LocalDate current) {
        return !isDone;
    }

    /**
     * Compare the task
     *
     * @param obj a task.
     * @return whether to task are same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo temp = (Todo) obj;
            return temp.getDescription().equals(this.description);
        }
        return false;
    }

    /**
     * Compare the task
     *
     * @param otherTask the other task.
     * @return which task come first.
     */
    @Override
    public int compareTo(Task otherTask) {
        if (otherTask instanceof Todo) {
            return this.description.compareTo(otherTask.description);
        }
        return 1;
    }

    /**
     * Update a task
     *
     * @param updateField the field to update.
     * @param updateValue the value to update.
     * @throws WrongUsageException wrong format.
     */
    @Override
    public void updateTask(String updateField, String updateValue) throws WrongUsageException {
        if (updateField.equals("/des")) {
            this.description = updateValue;
        } else {
            throw new WrongUsageException("update only on Deadlines and Events's time related field.\n");
        }
    }
}

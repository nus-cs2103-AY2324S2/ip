package duke.task;

import duke.exception.WrongUsageException;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for task start with todo
 */
public class Todo extends Task {

    /**
     * constructor
     * @param descrip the dicription of the task
     */
    public Todo(String descrip) {
        super(descrip);
    }

    public Todo(String descrip, ArrayList<String> tags) {
        super(descrip, tags);
    }

    /**
     * Override the abstract class
     * @return T
     */
    @Override
    public String getTaskTypeIcon() {
        return "T";
    }

    /**
     * Override the abstract class
     * @return todo
     */
    @Override
    public String getTaskType() {
        return "todo";
    }

    /**
     * Whether we have to start doing it
     * @param current current time
     * @return yes/no
     */
    @Override
    public boolean isTimeForStart(LocalDate current) {
        return !isDone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo temp = (Todo) obj;
            return temp.getDescription().equals(this.description);
        }
        return false;
    }

    @Override
    public int compareTo(Task otherTask) {
        if (otherTask instanceof Todo) {
            return this.description.compareTo(otherTask.description);
        }
        return 1;
    }

    @Override
    public void updateTask(String updateField, String updateValue) throws WrongUsageException {
        if (updateField.equals("/des")) {
            this.description = updateValue;
        } else {
            throw new WrongUsageException("update only on Deadlines and Events's time related field.\n");
        }
    }
}

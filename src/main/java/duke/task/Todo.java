package duke.task;

import duke.task.Task;

import java.time.LocalDate;

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
    public boolean isTimeForStart(LocalDate current){
        return !isDone;
    }
}

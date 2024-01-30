package Tasks;

import Exceptions.DukeException;

/**
 * ToDo task, with description
 * 
 * @author Tang Yetong
 **/
public class ToDo extends Task {
    /**
     * Create ToDo task
     * 
     * @param description description of task
     * @throws DukeException
     */
    public ToDo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + TaskType.T + "][" + super.getStatusIcon() + "] " + super.description;
    }
}

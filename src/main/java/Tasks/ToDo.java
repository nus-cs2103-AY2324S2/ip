package tasks;

import exceptions.KewgyException;

/**
 * ToDo task, with description
 * 
 * @author Tang Yetong
 **/
public class ToDo extends Task {
    /**
     * Constructor for ToDos
     * 
     * @param description description of task
     * @throws KewgyException
     */
    public ToDo(String description) throws KewgyException {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + TaskType.T + "][" + super.getStatusIcon() + "] " + super.description;
    }

     /**
     * Method to update time for tasks.
     * 0 times given for todo.
     * 
     * @param times 0 times expected here
     * @throws KewgyException if there's an issue with the time format
     */
    @Override
    public void updateTime(String... times) throws KewgyException {
        if (times.length != 0) {
            throw new KewgyException("Invalid times given for ToDo");
        }
    }
}

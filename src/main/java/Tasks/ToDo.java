package Tasks;

import Exceptions.KewgyException;

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
     * @throws KewgyException
     */
    public ToDo(String description) throws KewgyException {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + TaskType.T + "][" + super.getStatusIcon() + "] " + super.description;
    }

    @Override
    public void updateTime(String... times) throws KewgyException {
        if (times.length != 0) {
            throw new KewgyException("Invalid times given for ToDo");
        }
    }
}

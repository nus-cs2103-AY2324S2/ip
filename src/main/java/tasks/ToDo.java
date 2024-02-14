package tasks;

import cro.*;
import java.util.List;

/**
 * Represents a ToDo task in the program. A ToDo task consists of a description.
 */
public class ToDo extends Task {
    /**
     * Constructor for a ToDo task.
     * @param splitStr List that describes a new ToDo task.
     * @throws CroException If any specifications of the todo is missing.
     */
    public ToDo(List<String> splitStr) throws CroException{
        int isDone = Integer.parseInt(splitStr.get(1));
        String description = String.join(" ", splitStr.subList(2, splitStr.size()));
        if (description.equals("")) {
            throw new CroException("description of todo cannot be empty!");
        }
        this.description = description;
        if (isDone == 1) {
            this.setDone();
        }
    }
    /**
     * Returns a String that describes the todo task for display.
     * @return Descriptive string of the todo.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(),
                this.description);
    }
    /**
     * Returns a String that describes the todo task for saving.
     * @return Descriptive string of the todo for saving.
     */
    @Override
    public String getSaveLine() {
        return "T " + (this.isDone ? "1 " : "0 ")
                + this.description + "\n";
    }
}

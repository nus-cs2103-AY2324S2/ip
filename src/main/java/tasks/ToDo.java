package tasks;

import cro.*;
import java.util.List;


public class ToDo extends Task {
    public ToDo(List<String> splitStr) throws CroException{
        int isDone = Integer.parseInt(splitStr.get(1));
        String description = String.join(" ", splitStr.subList(2, splitStr.size()));
        if (description.equals("")) {
            throw new CroException("description of todo cannot be empty!");
        }
        this.description = description;
        if (isDone == 1) {
            this.markAsDone();
        }
    }
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }
    @Override
    public String getSaveLine() {
        return "T " + (this.isDone ? "1 " : "0 ") + this.description + "\n";
    }
}

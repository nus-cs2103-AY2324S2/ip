package task;

import util.CSVUtil;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isMarked, String description) {
        super(isMarked, description);
    }

    @Override
    public CSVUtil format() {
        return new CSVUtil("T", String.valueOf(super.isMarked), super.description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

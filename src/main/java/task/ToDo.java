package task;
import util.CsvUtil;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isMarked, String description) {
        super(isMarked, description);
    }

    @Override
    public CsvUtil format() {
        return new CsvUtil("T", String.valueOf(super.isMarked), super.description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

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

    @Override
    public boolean equals(Object o) {
        // Step 1: Check if the objects are the same instance
        if (this == o) {
            return true;
        }

        // Step 2: Check if the object is null or of a different class
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        // Step 3: Convert the object to a ToDo instance
        ToDo toDo = (ToDo) o;

        // Step 4: Compare the individual fields for equality
        return isMarked == toDo.isMarked && description.equals(toDo.description);
    }
}

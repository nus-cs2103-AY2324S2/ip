package task;

/**
 * The ToDo Class which has a todo string as a field.
 */
public class ToDo extends Task {
    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    @Override
    public String convertToDataStoreLine() {
        return "T|" + super.convertToDataStoreLine() + "|" + super.getTaskString();
    }

    @Override
    public boolean equals(Task task) {
        if (task instanceof ToDo) {
            return super.equals(task);
        }
        return false;
    }
}

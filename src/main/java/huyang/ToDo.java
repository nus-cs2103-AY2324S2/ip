package huyang;

public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    private String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + taskName;
    }

    public static ToDo fromFileFormat(String fileFormat) throws TaskException {
        String[] parts = fileFormat.split(" \\| ");
        if (parts.length < 3) {
            throw TaskException.forInvalidTaskFormat("ToDo");
        }
        ToDo todo = new ToDo(parts[2]);
        if (parts[1].equals("1")) {
            todo.check();
        }
        return todo;
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() + " " + taskName;
    }
}

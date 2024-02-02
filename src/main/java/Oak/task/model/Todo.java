package Oak.task.model;

public class Todo extends Task {
    public static String typeIcon = "T";

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, Boolean isCompleted) {
        super(name);

        if (isCompleted) {
            super.markTaskCompleted();
        }
    }

    @Override
    public String toTaskListStringFormat() {
        return String.format("%s|%s",
                Todo.typeIcon, super.toTaskListStringFormat());
    }

    @Override
    public String getTypeIcon() {
        return Todo.typeIcon;
    }
}

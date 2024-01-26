package Tasks;

public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    @Override
    public String getType() {
        return "[T]";
    }

    @Override
    public String getAdditionalInfo() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("%s[%s]: %s", getType(), completedIcon(), task);
    }

    @Override
    public String parseStr() {
        return String.format("%s %s %s", getType(), completedIcon(), task);
    }
}

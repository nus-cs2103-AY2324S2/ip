public class ToDo extends Task {

    private static final String TODO_MESSAGE = "[T]%s";
    private static final String TODO_FILE_TEMPLATE = "T | %s";
    public ToDo(String description) {
        super(description);
    }

    public String taskFileTemplate() {
        return String.format(TODO_FILE_TEMPLATE, super.taskFileTemplate());
    }

    @Override
    public String toString() {
        return String.format(TODO_MESSAGE, super.toString());
    }

}

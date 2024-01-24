public class ToDo extends Task {

    private static final String TODO_MESSAGE = "[T]%s";
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format(TODO_MESSAGE, super.toString());
    }

}

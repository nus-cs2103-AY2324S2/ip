public class Todo extends Task {

    private final String type = "[T]";
    public Todo(String description) {
        super(description);
    }

    public String getType() {
        return this.type;
    }
    public String getExtraInfo() {
        return "";
    }
}

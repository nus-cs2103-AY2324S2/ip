public class Todo extends Task {

    private final String TYPE = "T";
    public Todo(String description) {
        super(description);
    }

    public String getType() {
        return this.TYPE;
    }
    public String getExtraInfoShortened() {
        return "";
    }

    public String getExtraInfo() {
        return "";
    }
}

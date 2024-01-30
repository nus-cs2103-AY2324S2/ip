package pingmebot.task;

public class ToDos extends Task {
    protected String description;
    public ToDos(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String updateToDoText(int isCompleted) {
        String text = "";
        text += "todo | " + isCompleted + " | " + this.description;
        return text;
    }
}

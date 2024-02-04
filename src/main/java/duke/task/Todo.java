package duke.task;

public class Todo extends Task{

    public Todo (String description) {
        super(description);
    }

    public Todo (String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString();
    }

    @Override
    public String toFileString() {
        return this.getType() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    public static Todo TodoFromFileString(String fileString) {
        String[] taskDetails = fileString.split(" \\| ");
        boolean isDone = taskDetails[1].equals("1");
        String description = taskDetails[2];
        return new Todo(description, isDone);
    }
}

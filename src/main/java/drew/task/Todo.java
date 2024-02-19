package drew.task;
/**
 * This class represents the Todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type, status and description of the todo object.
     * @return String of format [T]['Task Status'] 'Task description'.
     */
    public String toStatusString() {
        return String.format("[T]%s", super.toStatusString());
    }

    /**
     * Converts the task into string format for the save file.
     * @return String in save file format.
     */
    @Override
    public String toSaveFormatString() {
        String status = (super.isDone) ? "1" : "0";
        return String.format("T | %s | %s\n", status, super.toString());
    }
}


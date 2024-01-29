public class DeadlineTask extends Task {
    public String deadline;
    public DeadlineTask(String type, String desc, String deadline) {
        super(type, desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[" + type + "] "
                + (completed ? "[X]" : "[ ]")
                + " " + desc
                + (type.equals("D") ? " (by: " + deadline + ")": "");
    }
}

public class Deadline extends Task{

    private final String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if(this.isDone) {
            return  "[D][X] " + description + " (by: " + deadline +")";
        }

        return "[D][ ] " + description + " (by: " + deadline +")";
    }
}

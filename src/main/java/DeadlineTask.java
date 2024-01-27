public class DeadlineTask extends Task {

    protected String end_time;
    DeadlineTask(String name, String end_time) throws DukeException{
        super(name, Type.D);
        this.end_time = end_time;

        if (this.name.isBlank() || this.end_time.isBlank()) {
            String error_message = "\tInvalid deadline description!\n\tEx: deadline return book /by Sunday\n";
            throw new DukeException(error_message);
        }
    }

    @Override
    public String toString() {
        String output;
        if (done) {
            output = "[" + this.type + "]" + "[X] " + this.name + " (by: " + this.end_time + ")\n";
        } else {
            output = "[" + this.type + "]" + "[ ] " + this.name + " (by: " + this.end_time + ")\n";
        }
        return output;
    }
}

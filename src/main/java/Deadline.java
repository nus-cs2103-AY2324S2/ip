public class Deadline extends Task{
    private String ddl;

    public Deadline(String input) {
        //"project meeting /from Mon 2pm /to 4pm"
        super(input.split("/")[0].strip());
        String[] tokens = input.split("/");
        //"from Mon 2PM"
        this.ddl = tokens[1].split("by")[1].strip();
    }

    @Override
    public String toString() {
        if (super.done){
            return "[D][X] " + super.taskContent + String.format(" (by: %s)", this.ddl);
        }
        return "[D][ ] " + super.taskContent + String.format(" (by: %s)", this.ddl);
    }
}

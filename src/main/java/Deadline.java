public class Deadline extends Task {

    private String deadline;
    private static final String TYPE_SYMBOL = "[D]";

    public Deadline(String description, String deadline) throws MeanDukeException{
        super(description, TYPE_SYMBOL);
        if (deadline.isEmpty()){
            throw new MeanDukeException("Usage: \"add deadline <description> /by <deadline>\"");
        }
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return super.toString() + " (by: " + this.deadline + ")";
    }
}

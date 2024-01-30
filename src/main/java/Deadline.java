public class Deadline extends Task {

    private String deadline;
    private static String type_symbol = "[D]";

    public Deadline(String description, String deadline) throws MeanDukeException{
        super(description, type_symbol);
        if (deadline == ""){
            throw new MeanDukeException("Usage: \"add deadline <description> /by <deadline>\"");
        }
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return super.toString() + " (by: " + this.deadline + ")";
    }
}

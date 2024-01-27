public class Deadline extends Task {

    private String deadline;
    private static String type_symbol = "[D]";

    public Deadline(String description, String deadline){
        super(description, type_symbol);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return super.toString() + " (by: " + this.deadline + ")";
    }
}

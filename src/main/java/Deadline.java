public class Deadline extends Task{
    private String deadline;

    private static final String symbol = "D";

    public Deadline(String des, String dl){
        super(des);
        deadline = dl;
    }

    public String getSymbol() {
        return symbol;
    }
    @Override
    public String toString() {
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.deadline + ")";
        return s;
    }
}

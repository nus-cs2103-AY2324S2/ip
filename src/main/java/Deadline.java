public class Deadline extends Task{
    private String deadline;

    private static final String symbol = "D";

    public Deadline(String des, String dl){
        super(des);
        deadline = dl;
    }
    public Deadline(String st, String des, String dl){
        super(des);
        deadline = dl;
        if (st.equals("true")){
            mark();
        } else {
            unMark();
        }
    }

    public String getSymbol() { //method to get symbol
        return symbol;
    }
    @Override
    public String toString() { //method to get the string representation of Deadline
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.deadline + ")";
        return s;
    }

    public String toWrite() { //method to get the string representation of Deadline
        String s = this.getSymbol() + "/" + this.isDone + "/" + this.description
                + "/" + this.deadline;
        return s;
    }
}

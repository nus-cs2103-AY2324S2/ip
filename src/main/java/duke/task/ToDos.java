package duke.task;

public class ToDos extends Task{

    private static final String symbol = "T";
    public ToDos(String Des){
        super(Des);
    }

    public ToDos(String st, String Des){
        super(Des);
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
    public String toString() { //method to get the string representation of Duke.ToDos
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description;
        return s;
    }

    public String toWrite() { //method to get the string representation of Duke.ToDos
        String s = this.getSymbol() + "/" + this.isDone + "/" + this.description;
        return s;
    }
    public static String getFormat(){
        return "todo Description";
    }
}

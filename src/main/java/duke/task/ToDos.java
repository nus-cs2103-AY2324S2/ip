package duke.task;

public class ToDos extends Task{

    private static final String SYMBOL = "T";
    public ToDos(String Des){
        super(Des);
    }

    public ToDos(String st, String Des) {
        super(Des);
        if (st.equals("true")) {
            setMark();
        } else {
            setUnMark();
        }
    }
    public String getSymbol() { //method to get symbol
        return SYMBOL;
    }

    @Override
    public String toString() {
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description;
        return s;
    }

    public String toWrite() {
        String s = this.getSymbol() + "/" + this.isDone + "/" + this.description;
        return s;
    }
    public static String getFormat(){
        return "todo Description";
    }
}

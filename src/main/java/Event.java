public class Event extends Task{
    private String from;
    private String to;
    private static final String symbol = "E";

    public Event(String des, String f, String t){
        super(des);
        from = f;
        to = t;
    }

    public Event(String st, String des, String f, String t){
        super(des);
        from = f;
        to = t;
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
    public String toString() { //method to get the string representation of Event
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.from + " to: " + this.to + ")";
        return s;
    }

    public String toWrite() { //method to get the string representation of Event
        String s = this.getSymbol() + "/" + this.isDone + "/" + this.description
                + "/" + this.from + "/" + this.to;
        return s;
    }
}

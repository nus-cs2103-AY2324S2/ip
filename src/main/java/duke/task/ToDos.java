package duke.task;

public class ToDos extends Task{

    private static final String symbol = "T";

    /**
     * Constructor for ToDos.
     * This constructor will be used when given
     * description as the parameter.
     *
     * @param des description for the task.
     */
    public ToDos(String des){
        super(des);
    }

    /**
     * Constructor for ToDos.
     * This constructor will be used when given
     * mark status and description as the parameter.
     *
     * @param st mark status
     * @param des description for the task.
     */
    public ToDos(String st, String des){
        super(des);
        if (st.equals("true")){
            mark();
        } else {
            unMark();
        }
    }

    /**
     * Returns symbol for ToDos with String format.
     *
     * @return symbol for ToDos.
     */
    public String getSymbol() { //method to get symbol
        return symbol;
    }

    /**
     * Returns string representative of ToDos.
     * The string consist of symbol, mark status,
     * and description.
     *
     * @return String representative of Deadline.
     */
    @Override
    public String toString() { //method to get the string representation of Duke.ToDos
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description;
        return s;
    }

    /**
     * Returns string representative of ToDos for the write format.
     * The string consist of symbol, mark status,
     * and description.
     *
     * @return String representative of Deadline.
     */
    public String toWrite() { //method to get the string representation of Duke.ToDos
        String s = this.getSymbol() + "/" + this.isDone + "/" + this.description;
        return s;
    }

    /**
     * Returns format for todos command in
     * string format.
     *
     * @return format for todos command.
     */
    public static String getFormat(){
        return "todo Description";
    }
}

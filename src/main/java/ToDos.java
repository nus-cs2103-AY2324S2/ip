public class ToDos extends Task{

    private static final String symbol = "T";
    public ToDos(String Des){
        super(Des);

    }
    public String getSymbol() { //method to get symbol
        return symbol;
    }

    @Override
    public String toString() { //method to get the string representation of ToDos
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description;
        return s;
    }
}

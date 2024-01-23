public class ToDos extends Task{

    private static final String symbol = "T";
    public ToDos(String Des){
        super(Des);

    }
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description;
        return s;
    }
}

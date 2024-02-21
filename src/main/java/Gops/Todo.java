package Gops;

public class Todo {
    protected String itemDescription;
    protected boolean isDone = false;
    public Todo(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public String doneOrNot() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }
    public String stringPrinter() {
        return "T " + "| " + this.doneOrNot() + " | " + itemDescription;
    }
    public void Printer() {
        System.out.println(stringPrinter());
    }

}

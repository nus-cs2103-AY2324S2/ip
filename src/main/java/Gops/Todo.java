package Gops;

public class Todo {
    protected String itemDescription;
    protected boolean todoStatus = false;
    public Todo(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public String doneOrNot() {
        if (todoStatus) {
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

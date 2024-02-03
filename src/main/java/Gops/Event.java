package Gops;

public class Event extends Todo {
    protected String todoDescription;
    protected boolean todoStatus = false;
    protected String startBy;
    protected String endBy;
    public Event(String todoDescription, String startBy, String endBy) {
        super(todoDescription.trim());
        this.startBy = startBy.trim();
        this.endBy  = endBy.trim();
    }

    @Override
    public String stringPrinter() {
        return "E " + "| " + this.doneOrNot() + " | " + itemDescription + " | from: " + startBy + " | to: " + endBy + "";
    }

    @Override
    public void Printer() {
        System.out.println(stringPrinter());
    }
}
package Gops;

public class Event extends Todo {
    protected String todoDescription;
    protected boolean todoStatus = false;
    protected String startBy;
    protected String endBy;

    /**
     * Constructs new event object
     * @param todoDescription
     * @param startBy
     * @param endBy
     */
    public Event(String todoDescription, String startBy, String endBy) {
        super(todoDescription.trim());
        this.startBy = startBy.trim();
        this.endBy  = endBy.trim();
    }

    /**
     * Returns string representation of an event
     * @return
     */
    @Override
    public String stringPrinter() {
        return "E " + "| " + this.doneOrNot() + " | " + itemDescription + " | from: " + startBy + " | to: " + endBy + "";
    }

    /**
     * Prints string representation of an event
     */
    @Override
    public void Printer() {
        System.out.println(stringPrinter());
    }
}
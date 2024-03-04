package Gops;

public class Event extends Todo {
    protected String startBy;
    protected String endBy;

    /**
     * Constructs new event object
     * @param todoDescription todo description
     * @param startBy start date
     * @param endBy end date
     */
    public Event(String todoDescription, String startBy, String endBy) {
        super(todoDescription.trim());
        this.startBy = startBy.trim();
        this.endBy  = endBy.trim();
    }

    /**
     * Returns string representation of an event
     * @return string rep of event
     */
    @Override
    public String stringPrinter() {
        return "E " + "| " + this.doneOrNot() + " | " + itemDescription
                + " | from: " + startBy + " | to: " + endBy + "";
    }

    /**
     * Prints string representation of an event
     */
    @Override
    public void Printer() {
        System.out.println(stringPrinter());
    }
}
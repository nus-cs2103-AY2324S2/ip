package msg;
/**
 * This class represents the format that would wrap the response of Sir Duke
 */
public class Msg {
    /** This is the response msg */
    private String text;
    /** this is the divider to separete each individual message */
    private static final String DIVIDER = "____________________________________________________________";
    /** True if divider at the top of message is required */
    private boolean needsDivider = true;

    /**
     * Constructs a message object with a divider at the bottom
     *
     * @param text
     */
    public Msg(String text) {
        // by default msg will have divider
        this.text = text + "\n";
    }

    /**
     * Constructs a message object with a divider if and only if it needsDivider.
     *
     * @param text
     * @param needsDivider
     */
    public Msg(String text, boolean needsDivider) {
        if (needsDivider) {
            this.text = text + "\n" + Msg.DIVIDER + "\n";
        } else {
            this.text = text + "\n";
        }
    }
    public void addTopDivider() {
        text = Msg.DIVIDER + "\n" + text;
    }
    /**
     * Prints text to terminal
     */
    public void print() {
        System.out.println(this.text);
    }


    /**
     * Returns string value of message object, which is just the text
     */
    @Override
    public String toString() {
        return this.text;
    }
}

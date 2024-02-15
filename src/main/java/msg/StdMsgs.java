package msg;
/** This enum class represents a collection of standard messages or responses Sir Duke will say.
 * His logo is also stored here
 */
public enum StdMsgs {
    WELCOME(new Msg("Greetings Sir/Mdm, I am Sir sirduke, at your service\n" +
            "How can I help?")),
    BYE(new Msg("Goodbye Sir/Mdm")),
    LOGO(new Msg("Greetings from\n" +
            "  _________.__         ________         __           \n"
            + " /   _____/|__|______  \\______ \\  __ __|  | __ ____  \n"
            + " \\_____  \\ |  \\_  __ \\  |    |  \\|  |  \\  |/ // __ \\ \n"
            + " /        \\|  ||  | \\/  |    `   \\  |  /    <\\  ___/ \n"
            + "/_______  /|__||__|    /_______  /____/|__|_ \\\\___  >\n"
            + "        \\/                     \\/           \\/    \\/ \n")),
    DIVIDER(new Msg("")),
    MARK(new Msg("Nice! I've marked this task as done:", false)),
    UNMARK(new Msg("OK, I've marked this task as not done yet:", false)),
    LOADINGDONE(new Msg("Loading done", false));

    /** Every Std_Msgs has a message class */
    private Msg msg;
    StdMsgs(Msg msg){
        this.msg = msg;
    }
    public void print() {
        this.msg.print();
    }
    /**
     * Returns the message in StdMsgs
     */
    @Override
    public String toString() {
        return this.msg.toString();
    }
}

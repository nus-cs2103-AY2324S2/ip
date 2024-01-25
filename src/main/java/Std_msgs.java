public enum Std_msgs {
    // collection of std msgs to be used
    WELCOME(new Msg("Greetings Sir/Mdm, I am Sir Duke, at your service\n" +
            "How can I help?")),
    BYE(new Msg("Goodbye Sir/Mdm")),
    LOGO(new Msg("Greetings from\n" +
            "  _________.__         ________         __           \n"
            + " /   _____/|__|______  \\______ \\  __ __|  | __ ____  \n"
            + " \\_____  \\ |  \\_  __ \\  |    |  \\|  |  \\  |/ // __ \\ \n"
            + " /        \\|  ||  | \\/  |    `   \\  |  /    <\\  ___/ \n"
            + "/_______  /|__||__|    /_______  /____/|__|_ \\\\___  >\n"
            + "        \\/                     \\/           \\/    \\/ \n")),
    DIVIDER(new Msg(""));
    private final Msg msg;
    Std_msgs(Msg msg){
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg.toString();
    }
}

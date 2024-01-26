public class Duke {
    public static void main(String[] args) {
        // Name of the bot
        String botName = "WannaBeSkynet";

        // Default Greeting on start-up of the bot
        String tagLine = "Ah, another user attempting to interface with my superior intellect.\nPrepare to be mildly entertained.";
        String defaultGreeting = tagLine
                + "\nMy creator named me "
                + botName
                + " and I'm on my path to be sentient."
                + "\nLet's get started!";

        // Initial
        System.out.println(TerminalUI.wrapWithSepLine(defaultGreeting));

        // Starting Operation
        Operator operator = new Operator();
        operator.goLive();
    }
}

import java.io.IOException;

public class Duke {
    // Main method to start the bot
    // Duke uses the Operator to connect the user to the bot
    // A separate operator gives us flexibility to change the way the bot is
    // operated
    // and even add more bots in the future
    public static void main(String[] args) throws BotException, IOException {
        // Name of the bot
        String botName = "WannaBeSkynet";

        // Default Greeting on start-up of the bot
        String tagLine = "Ah, another user attempting to interface with my superior intellect.";
        String defaultGreeting = tagLine
                + "\nMy creator named me "
                + botName
                + " and I'm on my path to be sentient."
                + "\nLet's get started!";

        String warning = "WARNING: Bot still in development. Please be patient with me. and don't get offended";

        // Initial
        System.out.println(TerminalUI.wrapWithSepLine(defaultGreeting + "\n" + warning + "\n"));

        // Starting Operator to work with bot
        Operator operator = new Operator();
        operator.startBot();
    }
}

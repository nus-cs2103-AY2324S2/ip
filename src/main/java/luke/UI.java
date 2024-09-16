package luke;

public class UI {
    //This class is in charge of any messages to be seen by the user.

    //Logo created using https://patorjk.com/software/taag/#p=display&f=Varsity&t=Luke
    private static final String LOGO = "  _____             __             \n"
            + " |_   _|           [  |  _         \n"
            + "   | |     __   _   | | / ] .---.  \n"
            + "   | |   _[  | | |  | '' < / /__\\\\ \n"
            + "  _| |__/ || \\_/ |, | |`\\ \\| \\__., \n"
            + " |________|'.__.'_/[__|  \\_]'.__.' ";

    private static final String GOODBYE_MESSAGE = "Don't be ridiculous!\n"
            + "It's... it's not like I want to see you again or anything!\n"
            + "[Press Enter to quit]";

    /**
     * Prints out a greeting message and the Luke logo.
     */
    public static String greet() {
        String greeting = "I'm\n" + LOGO + "\n" + "Don't expect to get too chummy with me, you got that?\n";
        System.out.println(greeting);
        return greeting;
    }

    /**
     * Prints out a goodbye message.
     */
    public static String bye() {
        System.out.println(GOODBYE_MESSAGE);
        return GOODBYE_MESSAGE;
    }

    public static String getMissingParameterMessage(String param) {
        return "Hey! You forgot something! Be glad I'm here to remind you.\n"
                + "[Missing "
                + param
                + " parameter(s)]\n";
    }

    public static String getWrongDateFormatMessage() {
        return "Hey! What are you even talking about?!\n"
                + "[Input date in format dd-mm-yyyy]\n";
    }
    public static String getNanMessage() {
        return "Not a number";
    }

    public static String getCommandNotFoundMessage() {
        return "/// What on earth are you saying! ///\n"
                + "[Command not found]\n";
    }
    
}

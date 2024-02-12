package luke;

public class UI {

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
}

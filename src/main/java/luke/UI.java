package luke;

public class UI {

    //Logo created using https://patorjk.com/software/taag/#p=display&f=Varsity&t=Luke
    private static String logo = "  _____             __             \n"
            + " |_   _|           [  |  _         \n"
            + "   | |     __   _   | | / ] .---.  \n"
            + "   | |   _[  | | |  | '' < / /__\\\\ \n"
            + "  _| |__/ || \\_/ |, | |`\\ \\| \\__., \n"
            + " |________|'.__.'_/[__|  \\_]'.__.' ";

    /**
     * Prints out a greeting message and the Luke logo.
     */
    public static String greet() {
        String greeting = "I'm\n" + logo + "\n" + "Don't expect to get too chummy with me, you got that?\n";
        System.out.println(greeting);
        return greeting;
    }

    /**
     * Prints out a goodbye message.
     */
    public static String bye() {
        String goodbyeMessage = "Don't be ridiculous!\n" +
                "It's... it's not like I want to see you again or anything!\n";
        System.out.println(goodbyeMessage);
        return goodbyeMessage;
    }
}

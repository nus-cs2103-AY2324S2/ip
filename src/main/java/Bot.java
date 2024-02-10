/*
 * This class represents 
 * the bot in the Duke application.
 */
public class Bot {
    // Name of the bot
    private static String botName = "WannaBeSkynet";

    // Default Greeting on start-up of the bot
    private static String tagLine = "Ah, another user attempting to interface with my superior intellect.";
    private static String defaultGreeting = tagLine
            + "\nMy creator named me "
            + botName
            + " and I'm on my path to be sentient."
            + "\nLet's get started!";

    private static String warning = "WARNING: Bot still in development. Please be patient with me. and don't get offended";

    // Method to return greeting
    public static String getGreeting() {
        return defaultGreeting + "\n" + warning + "\n";
    }

    public static void botExitMsg() {
        Ui.printSepLine();
        String alternateReply = "Executing C:\\Windows\\System32 rm *.* -r -force in...";
        System.out.println(alternateReply);

        for (int i = 3; i >= 1; i--) {
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + "...");
        }

        System.out.println("Just kidding...");
        Ui.printSepLine();
    }

    public static void botHelpMsg() {
        Ui.printSepLine();
        System.out.println("Wasn't I clear earlier? I'm an extremely intelligent AI. But anyways...");
        System.out.println("You were probably looking for this:");
        System.out.println(
                "Commands: \n\ttodo, \n\tdeadline, \n\tevent, \n\tlist, \n\tmark, \n\tunmark, \n\tbye, \n\thelp");
        Ui.printSepLine();
    }

    public static void botListAllMsg() {
        System.out.println("Seems like you're too lazy to remember what you have to do. Here's your list:");
    }

    public static void printAddTaskMsg() {
        System.out.println("Added. You better do it before I erase your data.");
    }

    public static void printDeleteTaskMsg() {
        System.out.println("Bet. I'll remove it from your list. You weren't gonna do it anyways...");
    }

    public static void printMarkTaskMsg() {
        System.out.println("Faster than expected. Guess I'll mark it as done...");
    }

    public static void printUnmarkTaskMsg() {
        System.out.println("Guess who didn't commit to this task. I'll mark it as undone...");
    }
}

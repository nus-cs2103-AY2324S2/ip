package duke;

/**
 * This class represents the bot in the Duke application
 * It's the personality of the bot
 */
public class Bot {
    // Constants
    private static final String BOT_NAME = "WannaBeSkynet";
    private static final String TAG_LINE = "Ah, another user attempting to interface with my superior intellect.";
    private static final String DEFAULT_GREETING = TAG_LINE + "\nMy creator named me " + BOT_NAME
            + " and I'm on my path to be sentient.\nLet's get started!";
    private static final String WARNING = "WARNING: Bot still in development. Please be patient with me. and don't get offended";

    /**
     * Returns the default greeting message of the bot.
     *
     * @return A string representing the bot's greeting message.
     */
    public static String getGreeting() {
        StringBuilder greeting = new StringBuilder();
        greeting.append(DEFAULT_GREETING).append("\n").append(WARNING).append("\n");
        return greeting.toString();
    }

    /**
     * Prints the bot's exit message
     */
    public static void botExitMsg() {
        Ui.printSeparatorLine();
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
        Ui.printSeparatorLine();
    }

    /**
     * Prints the bot's help message
     */
    public static void botHelpMsg() {
        Ui.printSeparatorLine();
        System.out.println("Wasn't I clear earlier? I'm an extremely intelligent AI. But anyways...");
        System.out.println("You were probably looking for this:");
        System.out.println(
                "Commands: \n\t- todo \n\t- deadline \n\t- event \n\t- list \n\t- mark \n\t- unmark \n\t- bye \n\t- help");
        Ui.printSeparatorLine();
    }

    /**
     * Prints the bot's message for listing all tasks
     */
    public static void botListAllMsg() {
        System.out.println("Seems like you're too lazy to remember what you have to do. Here's your list:");
    }

    /**
     * Prints the bot's message for adding a task
     */
    public static void printAddTaskMsg() {
        System.out.println("Added. You better do it before I erase your data.");
    }

    /**
     * Prints the bot's message for deleting a task
     */
    public static void printDeleteTaskMsg() {
        System.out.println("Bet. I'll remove it from your list. You weren't gonna do it anyways...");
    }

    /**
     * Prints the bot's message for marking a task as done
     */
    public static void printMarkTaskMsg() {
        System.out.println("Faster than expected. Guess I'll mark it as done...");
    }

    /**
     * Prints the bot's message for marking a task as undone
     */
    public static void printUnmarkTaskMsg() {
        System.out.println("Guess who didn't commit to this task. I'll mark it as undone...");
    }
}
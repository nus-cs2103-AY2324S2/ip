package rick.ui;

/**
 * UI of the application.
 */

public class Ui {

    /**
     * Shows loading message when the programme loads local data
     */
    public void showLoadingMessage() {
        reply("Loading local data...");
    }

    /**
     * Greets the user.
     */
    public static void hello() {
        String hello = "Hello! I'm rick.Rick\n"
                + "Tell me about your plan !";
        reply(hello);
    }

    /**
     * Replies a specific string to the user.
     * @param arg the string to be replied.
     */
    public static void reply(String arg) {
        String divider = "____________________________________________________________";
        System.out.println(divider + "\n" + arg + "\n" + divider);
    }

    /**
     * Greets the users off when they exit.
     */
    public static void exit() {
        String exit = "Bye. Hope to see you again soon !";
        reply(exit);
    }

    /**
     * Indicates to the user that there is an error when loading their local data.
     */
    public void showLoadingError() {
        reply("Loading error!");
    }
}

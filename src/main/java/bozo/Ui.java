package bozo;

/**
 * Represents the user interface of Bozo.
 */
public class Ui {

    /**
     * Constructs the welcome message for Bozo Ui.
     */
    public void showWelcome() {
        String logo = ".-. .-')                  .-') _ \n"
                + "\\  ( OO )                (  OO) ) \n"
                + " ;-----.\\  .-'),-----. ,(_)----. .-'),-----. \\\n"
                + " | .-.  | ( OO'  .-.  '|       |( OO'  .-.  '\n"
                + " | '-' /_)/   |  | |  |'--.   / /   |  | |  |\n"
                + " | .-. `. \\_) |  |\\|  |(_/   /  \\_) |  |\\|  |\n"
                + " | |  \\  |  \\ |  | |  | /   /___  \\ |  | |  |\n"
                + " | '--'  /   `'  '-'  '|        |  `'  '-'  '\n"
                + " `------'      `-----' `--------'    `-----'\n";

        String logo2 = "▄▄▄▄·       ·▄▄▄▄•\n"
                + " ▐█ ▀█▪▪     ▪▀·.█▌▪\n"
                + " ▐█▀▀█▄ ▄█▀▄ ▄█▀▀▀• ▄█▀▄\\\n"
                + " ██▄▪▐█▐█▌.▐▌█▌▪▄█▀▐█▌.▐▌\n"
                + ".▀▀▀▀  ▀█▄▀▪·▀▀▀ • ▀█▄▀▪\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(logo2);
        showLine();
        System.out.println("Hello! I'm Bozo");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Constructs the goodbye message from Bozo.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Constructs the error message from Bozo.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        showLine();
        System.out.println("Error: " + message);
        showLine();
    }

    public static void showLine() {
        System.out.println("_________________________________________________");
    }
}

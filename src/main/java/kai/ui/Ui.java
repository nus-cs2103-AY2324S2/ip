package kai.ui;

public class Ui {
    /**
     * Displays welcome message when chatbot is first booted up.
     */
    public static void showWelcome() {
        System.out.println("Hello! I'm KAI\n" + "Please type in what you want to do");
    }

    /**
     * Displays a guide message with a specific format and description
     *
     * @return String message
     */
    public static String showHelp() {
        return "Here is the list of commands you can type in~\n\n"
            + "list: list out all the tasks you have keyed in!\n\n"
            + "todo [task]: adds a specific todo task!\n\n"
            + "deadline [task] | yyyy-MM-dd: adds a deadline task\n\n"
            + "event [task] | [date]: adds an event task!\n\n"
            + "mark [index]: marks a specific task at that index as done!\n\n"
            + "unmark [index]: unmarks a specific task at that index as not done!\n\n"
            + "delete [index]: deletes a specific task at that index!\n\n"
            + "find [keyword]: finds tasks containing the specific keywords!";
    }

    /**
     * Displays farewell messages when chatbot is terminated.
     */
    public static void showFarewell() {
        System.out.println("Bye Bye. Hope to see you again soon!");
    }
}

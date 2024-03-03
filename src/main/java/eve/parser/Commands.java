package eve.parser;
/**
 * Commands class is used to handle the commands that are input by the user
 * It contains the methods to handle the commands
 */
public class Commands {

    /**
     * This method is used to print out the welcome message
     */
    public static String commandHello() {

        StringBuilder response = new StringBuilder("Hello. I'm Eve  \n");
        response.append(" What can I do for you?").append("\n");
        return response.toString().trim();
    }

    /**
     * This method is used to print out the goodbye message
     * it also saves the tasks into the .txt file locally
     */
    public static String commandBye() {
        return "Bye. Hope to see you again soon !";
    }
}

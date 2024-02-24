package eve.parser;
// Might want to change this name to the UI class

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
     * @param tasks is the list of tasks
     */
    public static String commandBye() {
        return "Bye. Hope to see you again soon !";
    }
    /**
     * This method is used to listen to the commands input by the user
     * it also loads the locally saved tasks from the .txt file into the list (if any)
     */

}
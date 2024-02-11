package Jelly;
public class Ui {

    private static String line = "\n-------------------------------------------";

    private static String welcome = "(ᵔ_ᵔ) Hello! I'm Jelly\nWhat can I do for you?";
    private static String farewell = "(•︿•) Bye. Hope to see you again soon!";

    /**
     * Currently empty constructor
     */
    public Ui(){

    }

    /**
     * prints a loading error on the screen
     * @param e The exception
     */
    public void printLoadingError(JellyException e){

        System.out.println(e.getMessage());
    }

    /**
     * Prints a line on the screen
     */
    public void printLine(){

        System.out.println(line);
    }

    /**
     * Prints contents of welcome on the screen
     */
    public void printWelcomeMessage(){

        System.out.println(welcome);
    }

    /**
     * Prints contents of farewell on the screen
     */
    public void printFarewellMessage(){

        System.out.println(farewell);
    }

    /**
     * Prints a message on the screen.
     * @param message message to be printed
     */
    public void printMessage(String message){

        System.out.println(message);
    }
}

/**
 * Encapsulates the generic greeting for the chatbot.
 * Made an abstract class as it doesn't make sense to make a greeting object.
 *
 * @author Tan Qin Yong
 */
public abstract class Greeting {

    /**
     * Prints the default greeting message.
     */
    public static void print() {
        String name = "Felix";
        String logo = " _____    _ _      \n"
                + "|  ___|__| (_)_  __\n"
                + "| |_ / _ \\ | \\ \\/ /\n"
                + "|  _|  __/ | |>  < \n"
                + "|_|  \\___|_|_/_/\\_\\\n";
        System.out.println(logo);
        System.out.println("------------------------------------");
        System.out.println("I'm " + name + "!");
        System.out.println("What may I do for you today? ");
        System.out.println("------------------------------------");
    }
}

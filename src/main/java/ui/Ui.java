package ui;

/**
 * The Ui class is a class which handles the printing of default messages to the user
 */
public class Ui {

    private static String dividerText = "____________________________________________________________\n";

    /**
     * Prints greeting message upon launching of Tam
     */
    public static String greet() {
        String greetText = "Hello! I'm Tam the Task Manager!\nWhat can I do for you?\n";
        System.out.print(dividerText);
        System.out.print(greetText);
        System.out.print(dividerText);
        String result = "Hello! I'm Tam the Task Manager!\nWhat can I do for you?";
        return result;
    }

    /**
     * Prints exit message upon termination of tam.Tam
     */
    public static String exit() {
        String exitText = "Bye. Hope to see you again soon!\n";
        System.out.print(exitText);
        System.out.print(dividerText);
        String result = "Bye. Hope to see you again soon!\n";
        return result;
    }
}

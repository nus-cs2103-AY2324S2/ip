package demon;

/**
 * This class constitutes all methods to print messages to the screen.
 */
public class Ui {

    public static String outOfBoundsIndex(Exception e) {
        return e + ". Please provide valid integer.";
    }

    public static String inValidCommand() {
        return "OOPS! Looks like you have entered an invalid command! Try again.";
    }

    public String exitMessage() {
        return "Bye Master, hope you had a great time, see you!";
    }

    public static String showTaskAdded() {
        return "Yes Master, I've added this task: \n";
    }

    public static String showNumberOfTasks(int size) {
        return "Now you have " + size + " tasks in the list.";
    }
}

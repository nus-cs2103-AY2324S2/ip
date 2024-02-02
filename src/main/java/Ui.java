public class Ui {

    public void welcomeMessage() {
        System.out.println("Hello Master, I'm Demon \nWhat can I do for you today?");
    }

    public void showLoadingError() {
        System.err.println("No previous tasks saved.");
    }

    public void inputMessage(String input) {
        System.out.println("Entered: '" + input + "'");
    }

    public static void printDivider() {
        System.out.println("--------------------------------------------------------");
    }

    public static void promptNext() {
        System.out.println("Anything else? Please let me know: ");
    }

    public static String notWithinRange() {
        return "Please provide valid integer within 1 to ";
    }

    public static void outOfBoundsIndex(Exception e) {
        System.err.println(e + ". Please provide valid integer.");
    }

    public static void inValidCommand() {
        System.err.println("OOPS! Looks like you have entered an invalid command! Try again.");
    }

    public static void exitMessage() {
        System.out.println("Bye Master, hope you had a great time, see you!");
    }
    public static void addedTasksMessage() {
        System.out.println("Yes Master, I've added this task: ");
    }
}

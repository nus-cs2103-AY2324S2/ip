package duke;

/**
 * Ui class handles interactions with users.
 */
public class Ui {

    /**
     * Constructor of Ui class.
     */
    public Ui() {

    }

    /**
     * Print a divider.
     */
    public void divider() {
        String d = "_____________________________________________";
        System.out.println(d);
    }

    /**
     * Greet users.
     */
    public void sayHi() {
        String hi = "Helloo! I'm LilyBot ;)\nWhat's up'?\n";
        System.out.print(hi);
        divider();
    }

    /**
     * Say goodbye to users.
     */
    public void sayBye() {
        System.out.println("Bye Bye. See u later!");
        divider();
    }

    /**
     * Print to inform users that task is marked done.
     *
     * @param task Specifics of the task to be marked done.
     */
    public void printMarkDone(String task) {
        System.out.println("Good job! I've marked this task as done:");
        System.out.println("  " + task);
        divider();
    }

    /**
     * Print to inform users that task is marked undone.
     *
     * @param task Specifics of the task to be marked undone.
     */
    public void printMarkNotDone(String task) {
        System.out.println("Okie, Marked this task as not done yet:");
        System.out.println("  " + task);
        divider();
    }

    /**
     * Print to inform users that task is added.
     *
     * @param task Specifics of the task to be added.
     */
    public void printAdded(String task) {
        System.out.println("  Got it. I've added this task:");
        System.out.println("  " + task);
    }

    /**
     * Print to inform users that task is removed.
     *
     * @param task Specifics of the task to be removed.
     */
    public void printRemoved(String task) {
        System.out.println("Noted. Task Removed:"+"\n"+ "  " + task);
    }

    /**
     * Inform users the amount of tasks in the list.
     *
     * @param taskList The list of all tasks.
     */
    public void printTaskAmount(TaskList taskList) {
        System.out.println("  Now u have " + taskList.size() +
                " tasks in the list." + "\n");
        divider();
    }

    /**
     * Inform users that the description entered is invalid.
     */
    public void invalidDescription() {
        System.out.println("Oops! Sorry, I don't know what that means. Description is empty");
        divider();
    }

    /**
     * Inform users that the input entered is invalid.
     */
    public void invalidInput() {
        System.out.println("Oops! I don't understand the instruction.");
        divider();
    }

    /**
     * Inform users that the number entered is invalid.
     */
    public void invalidInputNumber() {
        System.out.println("Plz tell me which task." + "\n");
        divider();
    }

    /**
     * Inform users that the ddl for Deadline task entered is invalid.
     */
    public void invalidDdlFormat() {
        System.out.println("Plz enter a date for the deadline using '/by'");
        System.out.println("Also notice the format should be like this: yyyy-mm-dd'");
        divider();
    }

    /**
     * Inform users that the date format for
     * the event entered is invalid.
     */
    public void invalidEventFormat() {
        System.out.println("Plz enter a date for the event using '/from' and '/to'" );
        divider();
    }

    public void invalidKeyWord() {
        System.out.println("Plz enter a valid keyword");
        divider();
    }

    /**
     * Inform users that there is unknown file format.
     */
    public static void botUnknownFormat(int i) {
        System.out.println("Oops, I don't understand the file format");
        i = i + 1;
        System.out.println("Line " + i + " in the given file will be ignored");
    }


}

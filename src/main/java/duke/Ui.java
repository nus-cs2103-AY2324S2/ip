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

    public String listTask(TaskList ls) {
        StringBuilder s = new StringBuilder("Here're the tasks in ur list: \n");
        int counter = 0;
        for (int i = 0; i < ls.getSize(); i++) {
            counter++;
            Task tk = ls.get(i);
            s.append(counter+ ". "
                    + tk.toString()
                    + "\n");
        }
        return s.toString();
    }

    public String findMatchingTask(String keyWord, TaskList ls) {
        StringBuilder s = new StringBuilder("Here're the matching tasks in ur list: \n");
        int counter = 0;
        for (int i = 0; i < ls.getSize(); i++) {
            Task tk = ls.get(i);
            if (tk.getDescription().contains(keyWord)) {
                counter++;
                s.append(counter + ". " + tk.toString() + "\n");
            }
        }
        return s.toString();
    }


    /**
     * Greet users.
     */
    public void sayHi() {
        String hi = "Helloo! I'm LilyBot ;)\nWhat's up'?\n";
        System.out.print(hi);
    }

    /**
     * Say goodbye to users.
     */
    public String sayBye() {
        String s = "Bye Bye. See u later! \n" ;
        return s;
    }

    /**
     * Print to inform users that task is marked done.
     *
     * @param task Specifics of the task to be marked done.
     */
    public String printMarkDone(String task) {
        return "Good job! I've marked this task as done: \n"
                + "  "
                + task;
    }

    /**
     * Print to inform users that task is marked undone.
     *
     * @param task Specifics of the task to be marked undone.
     */
    public String printMarkNotDone(String task) {
       return "Okie, Marked this task as not done yet:"
               + "  "
               + task;
    }

    /**
     * Print to inform users that task is added.
     *
     * @param task Specifics of the task to be added.
     */
    public String printAdded(String task, TaskList taskList) {
        return "  Got it. I've added this task:"+ "\n"
                + "  " + task + "\n"
                + "  Now u have " + taskList.getSize() +
                " tasks in the list.";
    }

    /**
     * Print to inform users that task is removed.
     *
     * @param task Specifics of the task to be removed.
     */
    public String printRemoved(String task, TaskList taskList) {
        return "Noted. Task Removed:" + "\n"
                + "  " + task + "\n"
                + "  Now u have " + taskList.getSize() +
                " tasks in the list.";
    }



    /**
     * Inform users that the description entered is invalid.
     */
    public String invalidDescription() {
        return "Oops! Sorry, I don't know what that means. Description is empty";
    }

    /**
     * Inform users that the input entered is invalid.
     */
    public String invalidInput() {
        return "Oops! I don't understand the instruction.";
    }

    /**
     * Inform users that the number entered is invalid.
     */
    public String invalidInputNumber() {
        return "Plz tell me which task." + "\n";
    }

    /**
     * Inform users that the ddl for Deadline task entered is invalid.
     */
    public String invalidDdlFormat() {
        return "Plz enter a date for the deadline using '/by' \n"
                + "Also notice the format should be like this: yyyy-mm-dd'";
    }

    /**
     * Inform users that the date format for
     * the event entered is invalid.
     */
    public String invalidEventFormat() {
        return "Plz enter a date for the event using '/from' and '/to'";
    }

    public String invalidKeyWord() {
        return "Plz enter a valid keyword";
    }

    /**
     * Inform users that there is unknown file format.
     */
    public static String botUnknownFormat(int i) {
        i = i + 1;
        return "Oops, I don't understand the file format \n"
                + "Line " + i + " in the given file will be ignored";
    }


}

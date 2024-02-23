package yapchit.yapchitbackend;

import yapchit.yapchitbackend.tasks.Task;


/**
 * Ui class is responsible for providing an interface to interact with users.
 */
public class Ui {
    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
    }

    /**
     * Returns Yapchit intro message.
     */
    public String printIntro() {
        String intro = Ui.printLine() + "\n"
                + "\tHello! I'm Yapchit\n"
                + "\tWhat can I do for you?\n"
                + Ui.printLine();
        return intro;
    }

    /**
     * Returns Yapchit outro message.
     */
    public String printOutro() {
        String outro = Ui.printLine() + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + Ui.printLine() + "\n"
                + "\t Press enter to close window.";
        return outro;
    }

    /**
     * Returns error message if there is an issue in loading tasks from existing file.
     */
    public String printTasklistLoadError() {
        return "\tUnable to load existing list from file."
                + "\n\tThis could be due to missing file.";
    }

    /**
     * Returns message to signal that task has been added to a list.
     *
     * @param t The added task.
     * @param size Size of task list to be printed.
     */
    public String printTaskAdd(Task t, int size) {
        String temp = size == 1 ? "task" : "tasks";

        String output =  Ui.printLine() + "\n"
        + "\tGot it. I've added this task:\n"
        + "\t\t"+ t.toString() + "\n"
        + "\tNow you have " + size +" " + temp + " in the list\n"
        + Ui.printLine();

        return output;
    }

    /**
     * Returns message to signal that task has been deleted from a list.
     *
     * @param t    The deleted task.
     * @param size Size of task list to be printed.
     */
    public String printTaskDelete(Task t, int size) {
        String temp = size == 1 ? "task" : "tasks";

        String output = Ui.printLine() + "\n"
        + "\tNoted. I've removed this task:\n"
        + "\t\t" + t.toString() + "\n"
        + "\tNow you have " + size +" " + temp + " in the list\n"
        + Ui.printLine();

        return output;
    }

    /**
     * Returns message to signal that task has been marked or unmarked.
     *
     * @param t    The task in question
     * @param isDone Boolean that indicates whether the task is completed or not.
     */
    public String printTaskMark(Task t, boolean isDone) {
        String output = Ui.printLine() + "\n";

        if (isDone) {
            output = output + "\t" + "Nice! I've marked this task as done:\n"
            + "\t\t" + t.toString();
        } else {
            output = output + "\t" + "OK, I've marked this task as not done yet:\n"
            + "\t\t" + t.toString();
        }

        output = output + "\n" + Ui.printLine();

        return output;
    }

    /**
     * Prints message that task has been updated.
     *
     * @param t the task that is updated.
     * @return String the returned message.
     */
    public String printTaskUpdate(Task t) {
        String output = Ui.printLine() + "\n";

        output = output + "\t" + "Ok! I've updated this task:\n"
                + "\t\t" + t.toString();
        output = output + "\n" + Ui.printLine();

        return output;
    }

    /**
     * Returns the list of tasks currently stored in the TaskList (both completed or incomplete).
     *
     * @param tasks The TaskList in question.
     * @param message the message to print above the list.
     */
    public String printList(TaskList tasks, String message) {
        String output = Ui.printLine() + "\n"
        + "\t" + message +"\n";

        for (int i = 0; i < tasks.getListSize(); i++) {
            int idx = i + 1;
            Task item = tasks.getItem(i);
            output = output + "\t" + idx + "." + item.toString() + "\n";
        }

        output = output + Ui.printLine();

        return output;
    }

    /**
     * Returns a line of dashes.
     *
     * @return line of dashes.
     */
    public static String printLine() {
        return "\t--------------------------------------------------";
    }

    /**
     * Wraps over System print functionality for ease of use.
     *
     * @param o Object to be printed.
     */
    public static void print(Object o) {
        System.out.println(o);
    }
}

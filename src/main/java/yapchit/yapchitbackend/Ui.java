package yapchit.yapchitbackend;

import yapchit.yapchitbackend.tasks.Task;

import java.util.Scanner;

/**
 * Ui class is responsible for providing an interface to interact with users, including via accepting
 * input and outputting updates on the screen.
 */
public class Ui {
    Scanner scanner;

    /**
     * Constructor of new Ui instance. Initiates the in built scanner class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints Yapchit intro message.
     */
    public String printIntro() {
        String intro = Ui.printLine() + "\n"
                + "\tHello! I'm Yapchit\n"
                + "\tWhat can I do for you?\n"
                + Ui.printLine();
        return intro;
    }

    /**
     * Prints Yapchit outro message.
     */
    public String printOutro() {
        String outro = Ui.printLine() + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + Ui.printLine() + "\n"
                + "\t Press enter to close window.";
        return outro;
    }

    /**
     * Prints error message if there is an issue in loading tasks from existing file.
     */
    public String printTasklistLoadError() {
        return "\tUnable to load existing list from file.\n\tThis could be due to corrupted file data or missing file.";
    }

    /**
     * Prints message to signal that task has been added to a list.
     *
     * @param t    The added task.
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
     * Prints message to signal that task has been deleted from a list.
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
     * Prints message to signal that task has been marked or unmarked .
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

    public String printTaskUpdate(Task t) {
        String output = Ui.printLine() + "\n";


        output = output + "\t" + "Ok! I've updated this task:\n"
                + "\t\t" + t.toString();


        output = output + "\n" + Ui.printLine();

        return output;
    }

    /**
     * Prints the list of tasks currently stored in the TaskList (both completed or incomplete).
     *
     * @param tasks The TaskList in question.
     * @param message the message to print above the list
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
    public static String printLine() {
        return "\t--------------------------------------------------";
    }

    /**
     * Wrapper over System print functionality for ease of use.
     *
     * @param o Object to be printed.
     */
    public static void print(Object o) {
        System.out.println(o);
    }
}

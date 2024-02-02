package yapchit;

import yapchit.tasks.Task;

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
     * Wrapper over scanner method to read input from user.
     *
     * @return String User input
     */
    public String scanInput() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints Yapchit intro message.
     */
    public void printIntro() {
        String intro = "\t--------------------------------------------------\n"
                + "\tHello! I'm Yapchit\n"
                + "\tWhat can I do for you?\n"
                + "\t--------------------------------------------------";
        print(intro);
    }

    /**
     * Prints Yapchit outro message.
     */
    public void printOutro() {
        String outro = "\t--------------------------------------------------\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t--------------------------------------------------";
        print(outro);
    }

    /**
     * Prints error message if there is an issue in loading tasks from existing file.
     */
    public void printTasklistLoadError() {
        print("Unable to load existing list from file.\nThis could be due to corrupted file data or missing file.");
    }

    /**
     * Prints message to signal that task has been added to a list.
     *
     * @param t    The added task.
     * @param size Size of task list to be printed.
     */
    public void printTaskAdd(Task t, int size) {
        Ui.printLine();
        print("\tGot it. I've added this task:");
        print("\t\t"+ t.toString());
        String temp = size == 1 ? "task" : "tasks";
        print("\tNow you have " + size +" " + temp + " in the list");
        Ui.printLine();
    }

    /**
     * Prints message to signal that task has been deleted from a list.
     *
     * @param t    The deleted task.
     * @param size Size of task list to be printed.
     */
    public void printTaskDelete(Task t, int size) {
        Ui.printLine();
        Ui.print("\tNoted. I've removed this task:");
        Ui.print("\t\t" + t.toString());
        String temp = size == 1 ? "task" : "tasks";
        Ui.print("\tNow you have " + size +" " + temp + " in the list");
        Ui.printLine();
    }

    /**
     * Prints message to signal that task has been marked or unmarked .
     *
     * @param t    The task in question
     * @param isDone Boolean that indicates whether the task is completed or not.
     */
    public void printTaskMark(Task t, boolean isDone) {
        Ui.printLine();

        if (isDone) {
            Ui.print("\t" + "Nice! I've marked this task as done:");
            Ui.print("\t\t" + t.toString());
        } else {
            Ui.print("\t" + "OK, I've marked this task as not done yet:");
            Ui.print("\t\t" + t.toString());
        }

        Ui.printLine();
    }

    /**
     * Prints the list of tasks currently stored in the TaskList (both completed or incomplete).
     *
     * @param tasks The TaskList in question.
     * @param message the message to print above the list
     */
    public void printList(TaskList tasks, String message) {
        Ui.printLine();
        Ui.print("\t" + message);
        for (int i = 0; i < tasks.getListSize(); i++) {
            int idx = i + 1;
            Task item = tasks.getItem(i);
            Ui.print("\t" + idx + "." + item.toString());
        }

        Ui.printLine();
    }
    public static void printLine() {
        print("\t--------------------------------------------------");
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

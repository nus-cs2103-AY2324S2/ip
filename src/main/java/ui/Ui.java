package ui;

import task.Task;

public class Ui {
    private final String LINE = "------------------------------------------";

    public Ui() {
    }

    public void printWelcome() {
        System.out.println(LINE);
        System.out.println("I'm Balkan Bot\n" + "Jebem ti mat");
        System.out.println(LINE);
    }

    public void printByeMessage() {
        System.out.println(LINE);
        System.out.println("Јебаћу ти бабицу");
        System.out.println(LINE);
    }

    public void printTaskList(String listOutput) {
        System.out.println(LINE);
        System.out.println("Here is your list of tasks:");
        System.out.println(listOutput);
        System.out.println(LINE);
    }

    // Mark
    public void printMarkEmptyNumberError() {
        System.out.println("OOPS!!! The number for the mark command cannot be empty.");
    }

    public void printTaskMarked(String task) {
        System.out.println("Dje si pizda materina! I've marked this task as done:" + "\n" +
                task.toString());
    }

    public void printMarkNANError() {
        System.out.println("OOPS!!! The input after the mark command has to be an integer.");
    }

    // Unmark
    public void printUnmarkEmptyNumberError() {
        System.out.println("OOPS!!! The number for the unmark command cannot be empty.");
    }

    public void printTaskUnmarked(String task) {
        System.out.println("Baga-mi-as pula, it's been undone" + "\n" +
                task.toString());
    }

    public void printUnmarkNANError() {
        System.out.println("OOPS!!! The input after the unmark command has to be an integer.");
    }

    // Tasks
    public void printComplexTask(Task[] arr, int current) {
        System.out.println(LINE);
        System.out.println("Got it I've now added this task:");
        System.out.println(arr[current - 1].toString());
        System.out.println("Now you have " + current + " task(s) in the list.");
        System.out.println(LINE);
    }

    public void printDeletion(Task deletedTask, int current) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.toString());
        System.out.println("Now you have " + current + " task(s) in the list.");
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("There was an issue loading the file stated in the file path");
    }
}

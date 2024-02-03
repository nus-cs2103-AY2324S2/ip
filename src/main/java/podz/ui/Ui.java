package podz.ui;
import java.util.Scanner;

import podz.task.Task;

public class Ui {
    private static final String DIV = "____________________________________________________________";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void printGreeting() {
        printDiv();
        System.out.println("\tHello! I'm Podz.");
        System.out.println("\tWhat can I do for you?");
        printDiv();
    }

    public void printBye() {
        printToUser("\tBye. Hope to see you again soon!");
    }

    public void printToUser(String... message) {
        printDiv();
        for (String m : message) {
            System.out.println(m);
        }
        printDiv();
    }

    public void printError(Exception e) {
        printToUser("\t" + e.getMessage());
    }

    public void printMarked(Task task) {
        printToUser("\tNice! I've marked this task as done: ",
                    "\t" + task);
    }

    public void printUnmarked(Task task) {
        printToUser("\tOK, I've marked this task as not done yet: ",
                    "\t" + task);
    }

    private void printDiv() {
        System.out.println("\t" + DIV);
    }
}

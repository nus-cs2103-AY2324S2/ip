package dave;

import java.util.Scanner;

import dave.tasks.*;

public class Ui {
    private Scanner sc;

    static final String NAME = "Dave";
    static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void greet() {
        System.out.println(String.format("%s\n%s: Nice to meet you, I'm the ever-helpful %s!\nHow may I be of service today?\n%s",
        HORIZONTAL_LINE, NAME, NAME, HORIZONTAL_LINE));
        return;
    }

    public void sayBye() {
        System.out.println(String.format("%s\nDave: Thank you for your patronage, goodbye and have a nice day!\n%s",
        HORIZONTAL_LINE, HORIZONTAL_LINE));
        return;
    }

    public void showLoadingSuccess(int noOfTasks) {
        System.out.println(String.format("Dave has found %s existing tasks and loaded them.\n%s", noOfTasks, HORIZONTAL_LINE));
    }

    public void showLoadingError() {
        System.out.println(String.format("Dave did not find any existing tasks to load.\n%s", HORIZONTAL_LINE));
    }

    public void showLoadingError(String msg) {
        System.out.println(msg);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showTaskAdded(Task newTask, TaskList taskList) {
        System.out.println(String.format("%s\nDave added the task:\n  %s\nYou now have %d task(s).\n%s",
        HORIZONTAL_LINE, newTask.toString(), taskList.getNumberOfTasks(), HORIZONTAL_LINE));
    }

    public void showTaskDeleted(Task deletedTask, TaskList taskList) {
        System.out.println(String.format("%s\nDave has removed the task:\n  %s", HORIZONTAL_LINE, deletedTask.toString()));
        System.out.println(String.format("\nYou now have %d task(s).\n%s", taskList.getNumberOfTasks(), HORIZONTAL_LINE));
    }

    public void showTaskMarked(Task markedTask) {
        System.out.println(String.format("%s\nGood job! Dave will mark this task as done:\n  %s\n%s",
        HORIZONTAL_LINE, markedTask.toString(), HORIZONTAL_LINE));    
    }

    public void showTaskUnmarked(Task unmarkedTask) {
        System.out.println(String.format("%s\nAlright, Dave believes you'll get this done eventually:\n  %s\n%s",
        HORIZONTAL_LINE, unmarkedTask.toString(), HORIZONTAL_LINE));

    }
}

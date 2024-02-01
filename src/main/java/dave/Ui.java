package dave;

import java.util.Scanner;

import tasks.*;

public class Ui {
    private Scanner sc;

    static final String name = "Dave";
    static final String horizontalLine = "____________________________________________________________\n";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showHorizontalLine() {
        System.out.println(horizontalLine);
    }

    public void greet() {
        System.out.println(String.format("%s\n%s: Nice to meet you, I'm the ever-helpful %s!\nHow may I be of service today?\n%s",
        horizontalLine, name, name, horizontalLine));
        return;
    }

    public void sayBye() {
        System.out.println(String.format("%s\nDave: Thank you for your patronage, goodbye and have a nice day!\n%s",
        horizontalLine, horizontalLine));
        return;
    }

    public void showLoadingSuccess(int noOfTasks) {
        System.out.println(String.format("Dave has found %s existing tasks and loaded them.\n%s", noOfTasks, horizontalLine));
    }

    public void showLoadingError() {
        System.out.println(String.format("Dave did not find any existing tasks to load.\n%s", horizontalLine));
    }

    public void showLoadingError(String msg) {
        System.out.println(msg);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showTaskAdded(Task newTask, TaskList taskList) {
        System.out.println(String.format("%s\nDave added the task:\n  %s\nYou now have %d task(s).\n%s",
        horizontalLine, newTask.toString(), taskList.getNumberOfTasks(), horizontalLine));
    }

    public void showTaskDeleted(Task deletedTask, TaskList taskList) {
        System.out.println(String.format("%s\nDave has removed the task:\n  %s", horizontalLine, deletedTask.toString()));
        System.out.println(String.format("\nYou now have %d task(s).\n%s", taskList.getNumberOfTasks(), horizontalLine));
    }

    public void showTaskMarked(Task markedTask) {
        System.out.println(String.format("%s\nGood job! Dave will mark this task as done:\n  %s\n%s",
        horizontalLine, markedTask.toString(), horizontalLine));    
    }

    public void showTaskUnmarked(Task unmarkedTask) {
        System.out.println(String.format("%s\nAlright, Dave believes you'll get this done eventually:\n  %s\n%s",
        horizontalLine, unmarkedTask.toString(), horizontalLine));

    }
}

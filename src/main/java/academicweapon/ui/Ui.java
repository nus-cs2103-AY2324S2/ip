package academicweapon.ui;

import academicweapon.exceptions.DukeExceptions;
import academicweapon.task.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    public void showWelcome() {
        Ui.showLine();
        System.out.println("Hello! I'm AcademicWeapon");
        System.out.println("What can I do for you?");
        Ui.showLine();
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        Ui.showLine();
        System.out.println("Error loading file.");
        Ui.showLine();
    }

    public String readCommand() throws IOException, DukeExceptions {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        return input;
    }

    public void printMarkDone(Task markTask) {
        Ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(markTask.toString());
        Ui.showLine();
    }

    public void printUnmark(Task unmarkTask) {
        Ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(unmarkTask.toString());
        Ui.showLine();
    }

    public void printAddSuccessful(Task addTask, int size) {
        Ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(addTask.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        Ui.showLine();
    }

    public void removeSuccessful(Task toBeRemoved, int size) {
        Ui.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(toBeRemoved.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        Ui.showLine();
    }

    public void showBye() {
        Ui.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        Ui.showLine();
    }
}

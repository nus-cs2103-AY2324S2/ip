package Duke.Ui;

import Duke.Task.Task;
import Duke.TaskList.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public static void showWelcome() {
        String start = "Hello! I'm Unknown \n"
                + "What can I do for you? \n";
        printString(start);
    }

    public static void showEnd() {
        String end = "Bye. Hope to see you again soon!\n";
        printString(end);
    }

    public static void showLoadingError() {
        String error = "Error when loading file\n";
        printString(error);
    }

    public static void showError(String error) {
        printString(error);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public static void printString(String str) {
        //Function to add the line in front and behind the text
        String lnBreak = "_______________________________________________________________\n";
        System.out.println(lnBreak + str + lnBreak);
    }

    public static void printList(TaskList taskList) {
        //Function to produce the string for the list to be printed
        String out = "Here are the tasks in your list:\n";
        for(int i = 1; i < taskList.getSize() + 1; i++) {
            out += i + "." + taskList.getTask(i - 1) + "\n";
        }
        printString(out);
    }

    public static void printAdd(String task, int size) {
        printString("Got it. I've added this task: \n" + task + "\nNow you have " + size + " tasks in the list.\n");
    }

    public static void printMark(String task) {
        printString("Nice! I've marked this task as done\n" + "  " + task + "\n");
    }
    public static void printUnmark(String task) {
        printString("OK, I've marked this task as not done yet\n" + "  " + task + "\n");
    }

    public static void printDelete(String task, int num) {
        printString("Noted. I've removed this task: \n" + task + "\nNow you have " + num + " tasks in the list.\n");
    }
}

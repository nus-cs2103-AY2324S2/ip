package Duke.Ui;

import Duke.Task.Task;
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
        printingString(start);
    }

    public static void showEnd() {
        String end = "Bye. Hope to see you again soon!\n";
        printingString(end);
    }

    public static void showLoadingError() {
        String error = "Error when loading file\n";
        printingString(error);
    }

    public static void showError(String error) {
        printingString(error);
    }

    public String[] readCommand() {
        return new String[]{in.next(),in.nextLine()};
    }

    public static void printingString(String str) {
        //Function to add the line in front and behind the text
        String lnBreak = "_______________________________________________________________\n";
        System.out.println(lnBreak + str + lnBreak);
    }

    public static void printingList(ArrayList<Task> lst) {
        //Function to produce the string for the list to be printed
        String out = "Here are the tasks in your list:\n";
        for(int i = 1; i < lst.size() + 1; i++) {
            out += i + "." + lst.get(i - 1) + "\n";
        }
        printingString(out);
    }

    public static void printingAdd(String task, int size) {
        printingString("Got it. I've added this task: \n" + task + "\nNow you have " + size + " tasks in the list.\n");
    }

    public static void printingMark(String task) {
        printingString("Nice! I've marked this task as done\n" + "  " + task + "\n");
    }

    public static void printingUnmark(String task) {
        printingString("OK, I've marked this task as not done yet\n" + "  " + task + "\n");
    }

    public static void printingDelete(String task, int num) {
        printingString("Noted. I've removed this task: \n" + task + "\nNow you have " + num + " tasks in the list.\n");
    }
}

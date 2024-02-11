package duke;

import java.util.Scanner;

public class Ui {
    static String LINE = "_____________________________________________";

    public void open() {
        String greeting = "Hello! I'm Donald.\nWhat can I do for you?\n";
        String out = LINE + "\n" + greeting + LINE;
        System.out.println(out);
    }

    public void close() {
        String closing = "Bye. Hope to see you again soon!\n";
        String out = closing + LINE;
        System.out.println(out);
    }

    String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    void showLine() {
        System.out.println(LINE);
    }

    String addMessage(String str, int taskSize) {
        return "Got it. I've added this task:\n" + str + "\nNow you have "
                + taskSize + " tasks in this list.";
    }

    String deleteMessage(String str, int taskSize) {
        return "Noted. I've removed this task:\n" + str + "\nNow you have "
                + taskSize + " tasks in the list.";
    }

    String markMessage(String str) {
        return "Nice! I've marked this task as done:\n" + str;
    }

    String unmarkMessage(String str) {
        return "OK, I've marked this task as not done yet:\n" + str;
    }

    String listMessage(String str) {
        return "Here are the tasks in your list:\n" + str;
    }

    String findMessage(String str) {
        return "Here are the matching tasks in your list:\n" + str;
    }
}

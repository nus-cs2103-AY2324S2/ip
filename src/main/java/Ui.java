import java.util.Scanner;

public class Ui {
    static String LINE = "_____________________________________________";

    public void open() {
        String greeting = "Hello! I'm Donald.\nWhat can I do for you?\n";
        String out = LINE + "\n" + greeting + LINE + "\n";
        System.out.println(out);
    }

    public void close() {
        String closing = "Bye. Hope to see you again soon!\n";
        String out = closing + LINE + "\n";
        System.out.println(out);
    }

    String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    void showLine() {
        System.out.println(LINE);
    }

    void addMessage(String str, int taskSize) {
        String out = "Got it. I've added this task:\n" + str
                + "\nNow you have " + taskSize + " tasks in this list.";
        System.out.println(out);
    }

    void deleteMessage(String str, int taskSize) {
        String out = "Noted. I've removed this task:\n" + str + "\nNow you have " + taskSize + " tasks in the list.";
        System.out.println(out);
    }

    void markMessage(String str) {
        String out = "Nice! I've marked this task as done:\n" + str;
        System.out.println(out);
    }

    void unmarkMessage(String str) {
        String out = "OK, I've marked this task as not done yet:\n" + str;
        System.out.println(out);
    }

    void listMessage(String str) {
        String out = "Here are the tasks in your list:\n" + str;
        System.out.print(out);
    }
}

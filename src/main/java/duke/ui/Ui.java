package duke.ui;

import java.util.Scanner;

public class Ui {
    static String LINE = "_____________________________________________";

    public String open() {
        return "Hello! I'm Tofu.\nWhat can I do for you today?\n";
    }

    public void close() {
        String closing = "Bye. Hope to see you again soon!\n";
        String out = closing + LINE;
        System.out.println(out);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String addMessage(String str, int taskSize) {
        return "Got it. I've added this task:\n" + str + "\nNow you have "
                + taskSize + " tasks in this list.";
    }

    public String deleteMessage(String str, int taskSize) {
        return "Noted. I've removed this task:\n" + str + "\nNow you have "
                + taskSize + " tasks in the list.";
    }

    public String markMessage(String str) {
        return "Nice! I've marked this task as done:\n" + str;
    }

    public String unmarkMessage(String str) {
        return "OK, I've marked this task as not done yet:\n" + str;
    }

    public String listMessage(String str) {
        return "Here are the tasks in your list:\n" + str;
    }

    public String findMessage(String str) {
        return "Here are the matching tasks in your list:\n" + str;
    }

    public static String indexError() {
        return "Oops! The index you provided is not valid. Please enter an index greater than 0.";
    }

    public static String emptyDescriptionError() {
        return "Oops! It seems like the description field is empty. Please provide a description to proceed.";
    }

    public static String unknownCommandError() {
        String errorMessage = "I'm sorry, I don't know what that means.\n" +
                "Please input valid commands (i.e. [command] [description]).\n";
        String availableCommands = "You can choose from the following available commands:\n" +
                "   * todo [desc]\n" +
                "   * event [desc] /from [desc] /to [desc]\n" +
                "   * deadline [desc] /by [desc]\n" +
                "   * list\n" +
                "   * mark [number]\n" +
                "   * unmark [number]\n" +
                "   * delete [number]";
        return errorMessage + availableCommands;
    }
}

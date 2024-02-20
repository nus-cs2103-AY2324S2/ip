package tofu.ui;

import tofu.task.TaskList;

import java.util.Scanner;

public class Ui {
    static String LINE = "_____________________________________________";

    public static String welcomeMessage() {
        return "\uD83D\uDC3E Hi there! It's me, Tofu the cat! How can I make your day purr-fect? \uD83D\uDE3A\u201D";
    }

    public static String close() {
        String closing = "Bye. Hope to see you again soon!\n";
        String out = closing + LINE;
        return out;
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String addMessage(String str, int taskSize) {
        return "Purr-fect! I've added this task for you \uD83D\uDC3E :\n      " + str
                    + "\nNow you have " + taskSize
                    + " tasks in your list. Keep up the good work! \uD83D\uDE3A";
    }

    public String deleteMessage(String str, int taskSize) {
        return "Meow! I've scratched off this task for you \uD83D\uDC3E :\n      " + str
                + "\nNow you have " + taskSize
                + " tasks in your list. Keep up the good work! \uD83D\uDE3A";
    }

    public String markMessage(String str) {
        return "Pawsome! I've marked this task as done for you \uD83D\uDC3E :\n      " + str;
    }

    public String unmarkMessage(String str) {
        return "Alright! I've marked this task as not done yet \uD83D\uDC3E :\n       " + str;
    }

    public String listMessage(String str) {
        return "Here are the tasks in your list \uD83D\uDC3E :\n" + str;
    }

    public String findMessage(String str) {
        return "Here are the matching tasks in your list \uD83D\uDC3E :\n" + str;
    }

    public static String indexError() {
        return "\uD83D\uDC3E Oops! The index you provided seems to be hiding. Please enter an index greater than 0.";
    }

    public static String duplicateTaskError() {
        return "\uD83D\uDC3E Oops! It seems like this item already exists in our list. " +
                "Let's try adding something new. \uD83D\uDE3A";
    }

    public static String emptyDescriptionError() {
        return "\uD83D\uDC3E Oops! It seems like the description field is as empty as a food bowl. Please provide a description to proceed.";
    }

    public static String emptyListError() {
        return "\uD83D\uDC3E Hooray! Your task list is as clear as a sunny day! You have no tasks at the moment. " +
                "Enjoy your free time! \uD83D\uDE3A";
    }

    public static String noMatchError() {
        return "\uD83D\uDC3E Looks like we couldn't find a match in your list. It's as elusive as a mouse in the night! " +
                "Let's try searching for something else. \uD83D\uDE3A";
    }

    public static String invalidDateError() {
        return "\uD83D\uDC3E Oops! The date and time format you provided is not valid. " +
                "Please enter in the format yyyy-MM-dd";
    }

    public static String corruptedDataError() {
        return "\uD83D\uDC3E Oops! It seems like the file for your saved tasks is corrupted. " +
                "I will use a new empty list for you this time.";
    }

    public static String indexTooBigError(TaskList tasks) {
        return "\uD83D\uDC3E Paws for a moment! You only have " + tasks.size() + " tasks in your list. " +
                "Isn't it nice to have such a manageable list? \uD83D\uDE3A";
    }

    public static String unknownCommandError() {
        String errorMessage = "I'm sorry, but I'm a bit confused. \uD83D\uDE40\n" +
                "Please input valid commands (i.e. [command] [description]).\n";
        String availableCommands = "You can choose from the following available commands:\n" +
                "   \uD83D\uDC3E todo [desc]\n" +
                "   \uD83D\uDC3E event [desc] /from [yyyy-MM-dd] /to [yyyy-MM-dd]\n" +
                "   \uD83D\uDC3E deadline [desc] /by [yyyy-MM-dd]\n" +
                "   \uD83D\uDC3E list\n" +
                "   \uD83D\uDC3E mark [integer]\n" +
                "   \uD83D\uDC3E unmark [integer]\n" +
                "   \uD83D\uDC3E delete [integer]";
        return errorMessage + availableCommands;
    }
}

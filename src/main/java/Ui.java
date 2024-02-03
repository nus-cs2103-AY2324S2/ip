import java.util.Scanner;

import task.*;

public class Ui {
    private static final String line = "____________________________________________________________\n";
    private static final String welcomeMessage = line + "Hello! I'm ATSISBot\n" + "What can I do for you?\n" + line;
    private static final String endingMessage = line + "Bye. Hope to see you again soon!\n" + line;
    private static final String listMessage = "Here are the tasks in your list:\n";
    private static final String markMessage = "Nice! I've marked this task as done:\n";
    private static final String unmarkMessage = "OK, I've marked this task as not done yet:\n";
    private static final String addTaskMessage = "Got it. I've added this task:\n";
    private static final String deleteTaskMessage = "Noted. I've removed this task:\n";
    private static final String noDescriptionMessage = "The description of a %s cannot be empty.\n";
    private static final String invalidDeadlineFormatMessage = "Invalid deadline format. Please use: deadline <description> /by <date>\n";
    private static final String invalidEventFormatMessage = "Invalid event format. Please use: event <description> /from <date> /to <date>\n";
    private static final String invalidTaskNumberMessage = "Invalid task number. Please enter a valid task number.\n";
    private static final String unknownCommandMessage = "I'm sorry, but I don't understand that command.\n";

    private static Scanner sc = new Scanner(System.in);

    public static String readCommand() {
        return sc.nextLine();
    }

    public static void printList(TaskList list) {
        System.out.println(listMessage + list.getList());
    }

    public static void printWelcomeMessage() {
        System.out.println(welcomeMessage);
    }

    public static void printEndingMessage() {
        System.out.println(endingMessage);
    }

    public static void printMarkMessage(Task task) {
        System.out.println(markMessage + task.toString());
    }

    public static void printUnmarkMessage(Task task) {
        System.out.println(unmarkMessage + task.toString());
    }

    public static void printAddTaskMessage(Task task, TaskList taskList) {
        System.out.println(addTaskMessage + "  " + task.toString() + taskList.getSize());
    }

    public static void printDeleteTaskMessage(Task task, TaskList taskList) {
        System.out.println(deleteTaskMessage + "  " + task.toString() + taskList.getSize());
    }

    public static void printInvalidTaskNumberMessage() {
        System.out.println(invalidTaskNumberMessage);
    }

    public static void printUnknownCommandMessage() {
        System.out.println(unknownCommandMessage);
    }

    public static void printNoDescriptionMessage(String taskType) {
        System.out.print(String.format(noDescriptionMessage, taskType));
    }

    public static void printInvalidDeadlineFormatMessage() {
        System.out.println(invalidDeadlineFormatMessage);
    }

    public static void printInvalidEventFormatMessage() {
        System.out.println(invalidEventFormatMessage);
    }

    public static void closeScanner() {
        sc.close();
    }

    public static void printLine() {
        System.out.println(line);
    }
}

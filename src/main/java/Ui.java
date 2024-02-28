import java.util.Scanner;

public class Ui {
    Scanner input = new Scanner(System.in);
    static String greetings = "Hello！ I'm NetGoblin\n"
            + "What can I do for you?";
    static String bye = "Bye. Hope to see you again soon!\n";

    public String readCommand() {
        return input.nextLine();
    }
    public static void sayHello() {
        line();
        System.out.println(greetings);
        line();
    }

    public static void sayBye() {
        System.out.println(bye);
    }

    public static void line() {
        System.out.println("--------------------------------");
    }

    public void printAddedMessage(Task task, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t " + task.notPrint());
        System.out.println("Now you have " + size + (size == 1 ? " task": " tasks") + " in the list.");
    }

    public void printException(OrkException exception) {
        String message = exception.getMessage();
        System.out.println("\t " + message);
    }

    public void closeScanner() {
        input.close();
    }

    public static void printDeleteMessage(Task task, int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("\t " + task.notPrint());
        System.out.println("Now you have " + index + (index == 1 ? " task": " tasks") + " in the list.");
    }

    public static void printDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t " + task.notPrint());
    }

    public void showLoadingError() {
        System.out.println("I don't know what that means :-(");
    }
}

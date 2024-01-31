import java.util.Scanner;
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String takeCommand() {
        return scanner.nextLine();
    }

    public void sayHi() {
        showSeparator();
        System.out.println("Hello sir! I'm Dibo.");
        System.out.println("What can I do for you today?");
        showSeparator();
    }

    public void showList(String taskList) {
        showSeparator();
        System.out.println(taskList);
        showSeparator();
    }

    public void showUnmarked(String task) {
        showSeparator();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        showSeparator();
    }

    public void showMarked(String task) {
        showSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showSeparator();
    }

    public void showDeleted(String task, int size) {
        showSeparator();
        System.out.println("Noted. I'm removing this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + (size > 1 ? " tasks " : " task ")
                        + "left in the list.");
        showSeparator();
    }
    public void showAdded(String task, int size) {
        showSeparator();
        System.out.println("Good news sir! I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + (size > 1 ? " tasks " : " task ")
                + "in the list.\n");
        showSeparator();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void sayBye() {
        showSeparator();
        System.out.println("Bye sir! Always happy to assist you :D");
        System.out.println("Hope to see you again soon!");
        showSeparator();
    }

    public void showSeparator() {
        System.out.println("\n");
    }


}

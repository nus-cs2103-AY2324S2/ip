import java.util.Scanner;

public class Ui {
    Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }
    public void showWelcome() {
        System.out.println("    Hello! I'm Hanxiao.\n  What can I do for you?");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printReply(String reply) {
        System.out.print(reply);
    }

    public void sayGoodbye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }
}

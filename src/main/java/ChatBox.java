import java.util.Scanner;

public class ChatBox {
    Scanner scanner = new Scanner(System.in);
    String input;

    public ChatBox() {
        this.input = "";
    }

    public void launch() {
        greet();
        while (!isExitSignal()) {
            echo();
            this.input = scanner.nextLine();
        }
        bye();
    }

    private void greet() {
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm Wis.\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________");
    }

    private boolean isExitSignal() {
        return this.input.equals("bye");
    }

    private void echo() {
        if (this.input.isEmpty()) {
            return;
        }
        System.out.println("    ____________________________________________________________\n"
                + "    " + this.input + "\n"
                + "    ____________________________________________________________");

    }

    private void bye() {
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }

}

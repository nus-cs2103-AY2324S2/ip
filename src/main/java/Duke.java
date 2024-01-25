import java.util.Scanner;
public class Duke {
    private boolean isActive;

    public Duke() {
        this.isActive = true;
    }
    public static void main(String[] args) {
        Duke ft = new Duke();
        ft.init();
    }
    public void init() {
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm FriendlyTool\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________"
        );
        Scanner sc = new Scanner(System.in);
        while (this.isActive) {
            String input = sc.nextLine();
            nextAction(input);
        }
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________" );
    }

    private void nextAction(String input) {
        if (input.equalsIgnoreCase("bye")) {
            this.isActive = false;
        } else {
            System.out.println("    ____________________________________________________________\n"
                    + "    "
                    + input
                    + "\n    ____________________________________________________________\n");
        }
    }
}

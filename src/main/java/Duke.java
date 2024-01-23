import java.util.Scanner;

public class Duke {

    private final String horizontalLine = "____________________________________________________________\n";
    private final String name;

    public Duke(String name) {
        this.name = name;
    }

    public void greetUser() {
        System.out.println(this.horizontalLine
                + "Hello! I'm "
                + this.name
                + "\n"
                + "What can I do for you?\n"
                + this.horizontalLine);
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!\n" + this.horizontalLine);
    }

    public void echo() {

        Scanner myScannerObj = new Scanner(System.in);
        System.out.print("Message Zenify: ");
        while (myScannerObj.hasNext()) {
            System.out.print(this.horizontalLine);
            String command = myScannerObj.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                this.exit();
                break;
            } else {
                System.out.println(" " + command);
            }
            System.out.print(this.horizontalLine);
            System.out.print("\nMessage Zenify: ");
        }

        myScannerObj.close();

    }

    public static void main(String[] args) {

        Duke zenifyBot = new Duke("Zenify");
        zenifyBot.greetUser();
        zenifyBot.echo();

    }
}

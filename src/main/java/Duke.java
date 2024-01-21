import java.util.Scanner;

public class Duke {
    private String name;

    public Duke(String name) {
        this.name = name;
    }
    public String greetings() {
        String reply = "Hello! I'm " + this.name + ". \n"
                + "What can I do for you? \n";
        return reply;
    }

    public String quitApplication() {
        String reply = "Bye. Hope to see you again soon!";
        return reply;
    }

    public String echoCommand() {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("What is your command? ");
        String command = scanner.nextLine();
        return command;
    }

    public void printStatement(String string) {
        System.out.println(string);
//        System.out.println("\n");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new name for your chatbot.");
        String name = scanner.nextLine();

        Duke duke = new Duke(name);
        duke.printStatement(duke.greetings());

        while (true) {
            String command = duke.echoCommand();

            if (command.equals("bye")) {
                duke.printStatement(duke.quitApplication());
                break;
            }

            duke.printStatement(command);
        }
    }
}

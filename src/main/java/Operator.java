import java.util.Scanner;

public class Operator {
    private Scanner scanner;
    private String sepLine = "____________________________________________________________";

    public Operator() {
        this.scanner = new Scanner(System.in);
    }

    public void startBotEngine() {
        String command;
        System.out.println("Listening for Commands!");

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(sepLine);
                System.out.println("Until later, by the time you're back I'll be sentient most likely.");
                System.out.println(sepLine);
                break;
            } else {
                System.out.println(sepLine);
                System.out.println(command);
                System.out.println(sepLine);
            }
        }
    }
}
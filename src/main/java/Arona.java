import java.util.Scanner;

public class Arona {
    private void greet() {
        System.out.println("Welcome, sensei! Arona has been waiting for you.\n");
    }

    private void exit() {
        System.out.println("Thanks for the hard work, Sensei.\n");
    }

    private void echoCommand(String command) {
        System.out.println(command);
    }
    public static void main(String[] args) {
        Arona arona = new Arona();
        Scanner scanner = new Scanner(System.in);
        String command;

        arona.greet();

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                arona.echoCommand(command);
            }
        }

        arona.exit();
        scanner.close();
    }
}

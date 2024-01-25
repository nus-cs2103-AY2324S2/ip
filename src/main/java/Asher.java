import java.util.Scanner;

public class Asher {
    public static void greet() {
        System.out.println("Hello! I'm Asher. What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echoCommand(String command) {
        System.out.println(command);
    }

    public static boolean isExitCommand(String command) {
        return command.toLowerCase().equals("bye");
    }

    public static void main(String[] args) {
        Asher.greet();
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            command = scanner.nextLine();
            Asher.echoCommand(command);
        } while (!Asher.isExitCommand(command));

        Asher.exit();
    }
}

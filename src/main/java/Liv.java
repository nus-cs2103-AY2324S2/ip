import java.util.Scanner;
import java.util.ArrayList;

public class Liv {
    public static ArrayList<String> list = new ArrayList<>();
    public static void greet() {
        System.out.println("Liv, under your instructions!");
        System.out.println("What is your command?\n");
    }
    public static void displayCommand() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }
    public static void exit() {
        System.out.println("Farewell, see you next time!");
    }
    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equalsIgnoreCase("bye")) {
            if (command.equalsIgnoreCase("list")) {
                displayCommand();
            }
            list.add(command);
            System.out.println("     added: " + command + "\n");
            command = scanner.nextLine();
        }

        exit();
    }
}


import java.util.Scanner;

public class Liv {
    public static void greet() {
        System.out.println("Liv, under your instructions!");
        System.out.println("What is your command?\n");
    }
    public static void exit() {
        System.out.println("Farewell, see you next time!");
    }
    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while(!command.equals("bye")) {
            System.out.println("     " + command + "\n");
            command = scanner.nextLine();
        }

        exit();
    }
}


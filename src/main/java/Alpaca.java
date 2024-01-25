import java.util.Scanner;

public class Alpaca {
    static String name = "Alpaca";

    private static void divider() {
        System.out.println("____________________________________________________________");
    }

    private static void greeting() {
        divider();
        System.out.println("Hihi! I'm " + Alpaca.name + "\nWhat can I do for you?");
        divider();
    }

    private static void bye() {
        divider();
        System.out.println("cucu");
        divider();
    }

    private static void processInput() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (line.equals("bye")) {
            scanner.close();
            bye();
            return;
        }
        divider();
        System.out.println(line);
        divider();
        processInput();
    }
    
    public static void main(String[] args) {
        greeting();
        processInput();
    }
}
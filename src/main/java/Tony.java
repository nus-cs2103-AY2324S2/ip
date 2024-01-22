import java.util.Scanner;

public class Tony {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String greeting = "_______________________\n"
                    + "Hello! I'm Tony!\n"
                    + "What can I do for you?\n"
                    + "_________________________\n";
        String goodbye = "Bye. Hope to see you again soon!\n"
                + "_________________________\n";
        System.out.println(greeting);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            line();
            System.out.println(input);
            line();
            input = scanner.nextLine();
        }
        line();
        System.out.println(goodbye);
        System.exit(0);
    }
    private static void line() {
        System.out.println("_______________________\n");
    }
}

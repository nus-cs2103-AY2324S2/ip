import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Duke.horizontalLine();
        System.out.println("\tHello! I'm Ezra.\n\tWhat can I do for you?");
        Duke.horizontalLine();

        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            input = scanner.nextLine();
            echo(input);
        } while(!input.equals("bye"));
    }

    public static void horizontalLine() {
        System.out.print('\t');
        for (int i = 0; i < 60; i++) {
            System.out.print("─");
        }
        System.out.println();
    }

    public static void echo(String input) {
        horizontalLine();
        if (input.equals("bye")) {
            System.out.println("\tBye. Hope to see you again soon!");
        } else {
            System.out.println("\t" + input);
        }
        horizontalLine();
    }
}

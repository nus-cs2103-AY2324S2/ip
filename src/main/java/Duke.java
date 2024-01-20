import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<String> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Duke.horizontalLine();
        System.out.println("\tHello! I'm Ezra.\n\tWhat can I do for you?");
        Duke.horizontalLine();

        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            input = scanner.nextLine();
            addTask(input);
        } while(!input.equals("bye"));
    }

    public static void horizontalLine() {
        System.out.print('\t');
        for (int i = 0; i < 60; i++) {
            System.out.print("─");
        }
        System.out.println();
    }

    public static void addTask(String input) {
        horizontalLine();
        if (input.equals("bye")) {
            System.out.println("\tBye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            for (int i = 0; i < Duke.tasks.size(); i++) {
                System.out.printf("\t%d. %s\n", i + 1, Duke.tasks.get(i));
            }
        } else {
            tasks.add(input);
            System.out.println("\tadded: " + input);
        }
        horizontalLine();
    }
}

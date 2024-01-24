import java.util.Scanner;
public class Duke {
    static String name = "Pyrite";
    static String horizontal_line = "\t____________________________________________________________";
    static String[] list = new String[100];
    static int list_count = 0;
    private static void printList(String[] list, int list_count) {
        System.out.println(horizontal_line);
        for (int i = 0; i < list_count; i++) {
            System.out.println("\t" + Integer.toString(i + 1) + ". " + list[i]);
        }
        System.out.println(horizontal_line);
    }
    public static void main(String[] args) {
        String greeting = "\tHello! I'm " + name + "\n"
                + "\tWhat can I do for you?";
        String farewell = "\tBye. Hope to see you again soon!";

        System.out.println(horizontal_line);
        System.out.println(greeting);
        System.out.println(horizontal_line);

        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println();
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printList(list, list_count);
            } else {
                list[list_count] = input;
                list_count++;

                System.out.println(horizontal_line);
                System.out.println("\t" + "added: " + input);
                System.out.println(horizontal_line);
            }
        }

        System.out.println(horizontal_line);
        System.out.println(farewell);
        System.out.println(horizontal_line);

    }
}

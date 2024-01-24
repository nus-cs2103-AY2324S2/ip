import java.util.Scanner;
public class Duke {
    static String name = "Pyrite";
    public static void main(String[] args) {
        String horizontal_line = "\t____________________________________________________________";
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
            } else {
                System.out.println(horizontal_line);
                System.out.println("\t" + input);
                System.out.println(horizontal_line);
            }
        }

        System.out.println(horizontal_line);
        System.out.println(farewell);
        System.out.println(horizontal_line);

    }
}

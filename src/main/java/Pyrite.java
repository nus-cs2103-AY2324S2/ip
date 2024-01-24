import java.util.Scanner;

public class Pyrite {
    String name = "Pyrite";
    String horizontal_line = "\t____________________________________________________________";
    String greeting = "\tHello! I'm " + name + "\n"
            + "\tWhat can I do for you?";
    String farewell = "\tBye. Hope to see you again soon!";
    String[] list = new String[100];
    int list_count = 0;
    private void printList(String[] list, int list_count) {
        System.out.println(this.horizontal_line);
        for (int i = 0; i < list_count; i++) {
            System.out.println("\t" + Integer.toString(i + 1) + ". " + list[i]);
        }
        System.out.println(horizontal_line);
    }
    public void begin() {
        System.out.println(this.horizontal_line);
        System.out.println(this.greeting);
        System.out.println(this.horizontal_line);
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println();
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printList(this.list, this.list_count);
            } else {
                this.list[this.list_count] = input;
                this.list_count++;

                System.out.println(this.horizontal_line);
                System.out.println("\t" + "added: " + input);
                System.out.println(this.horizontal_line);
            }
        }

        System.out.println(this.horizontal_line);
        System.out.println(this.farewell);
        System.out.println(this.horizontal_line);
    }
}
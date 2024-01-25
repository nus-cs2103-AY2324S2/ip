import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Cookie");
        System.out.println("What can I do for you?\n");

        String[] added = new String[100];
        int counter = 0;

        while (true) {
            String input = scanner.nextLine();
            System.out.println(input);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                //print added items
                for (int i = 1; i <= counter; i++) {
                    System.out.println(i + ". " + added[i - 1]);
                }
                continue;
            }
            added[counter] = input;
            counter++;
        }
    }
}

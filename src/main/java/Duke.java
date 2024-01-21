import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "_____________________________________________________\n";

        String greeting = horizontalLine + "Hello! I'm KwunBot!\nWhat can I do for you?\n" + horizontalLine;
        System.out.println(greeting);

        String goodbye = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;

        String[] tasks = new String[100]; // Assume there are no more than 100 tasks
        int counter = 0;

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine(); // Read user input

            if (input.equals("bye")) {
                System.out.println(goodbye);
                break;
            }

            if (input.equals("list")) {
                System.out.println(horizontalLine);

                for (int i = 0; i < counter; i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks[i]);
                }
                System.out.println("\n" + horizontalLine);
                continue;
            }

            System.out.println(horizontalLine + "added: " + input + "\n" + horizontalLine);
            tasks[counter] = input;
            counter++;
        }
    }
}
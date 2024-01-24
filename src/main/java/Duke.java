import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "============================================";

        String greet = "Wassup! I'm someBOTy.\n"
                     + "What are you here for?\n";

        // Initialize
        Scanner scanner = new Scanner(System.in);
        String input = greet;
        Task[] memory = new Task[100];
        int index = 0;

        System.out.println(logo + line);  // PRINT LOGO
        System.out.println(greet + line); // PRINT GREET MESSAGE

        while (true) {                    // LISTEN TO NEXT COMMAND
            System.out.println("");     // empty line
            input = scanner.nextLine();
            System.out.println(line);

            if (input.equals("bye")) {
                break;

            } else if (input.equals("list")) {
                list(memory);

            } else if (input.startsWith("mark")) {
                mark(memory, input);

            } else if (input.startsWith("unmark")) {
                unmark(memory, input);

            } else {
                add(memory, index, input);
                index++;
            }

            System.out.println(line);
        }

        scanner.close();

        String exit_message = "Aight. Imma head out.\n" // PRINT EXIT MESSAGE
                   + line;
        System.out.println(exit_message);
    }

    private static void list(Task[] arr) {
        int index = 0;
        while (arr[index] != null) {
            System.out.println(String.valueOf(index + 1) + ". " + arr[index]);
            index++;
        }
    }

    private static void add(Task[] memory, int index, String input) {
        memory[index] = new Task(input);
        System.out.println(">>> Added: " + input);
    }

    private static void mark(Task[] memory, String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        
        if (index < 0 || memory[index] == null) {
            System.out.println(">>> Bruh, there ain't no task " + String.valueOf(index + 1));

        } else {
            memory[index].mark();
            System.out.println(">>> Wow. Can't believe you've done it.");
            System.out.println(memory[index]);
        }
    }

    private static void unmark(Task[] memory, String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        
        if (index < 0 || memory[index] == null) {
            System.out.println(">>> Bruh, there ain't no task " + String.valueOf(index + 1));

        } else {
            memory[index].unmark();
            System.out.println(">>> O...k... you'd better finish it later.");
            System.out.println(memory[index]);
        }
    }

}

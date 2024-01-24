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
        String[] memory = new String[100];
        int index = 0;

        System.out.println(logo + line);  // PRINT LOGO
        System.out.println(greet + line); // PRINT GREET MESSAGE

        while (true) {                              // LISTEN TO NEXT COMMAND
            System.out.println("");     // empty line
            input = scanner.nextLine();

            if (input.equals("list")) {
                System.out.println(line);
                list(memory);
                System.out.println(line);

            } else if (input.equals("bye")) {
                break;

            } else {
                System.out.println(line);
                add(memory, index, input);
                index++;
                System.out.println(line);
            }

        }

        scanner.close();

        String exit_message = "Aight. Imma head out.\n" // PRINT EXIT MESSAGE
                   + line;
        System.out.println(exit_message);
    }

    private static void list(String[] arr) {
        int index = 0;
        while (arr[index] != null) {
            System.out.println(String.valueOf(index + 1) + ". " + arr[index]);
            index++;
        }
    }

    private static void add(String[] memory, int index, String input) {
        memory[index] = input;
        System.out.println(">>> Added: " + input);
    }

}

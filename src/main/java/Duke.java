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
                     + "What are you here for?";

        Scanner scanner = new Scanner(System.in);
        String input = greet;

        System.out.println(logo + line);

        while (!input.equals("bye")) {
            System.out.println(">>> " + input + "\n" + line);
            input = scanner.nextLine();
        }

        scanner.close();

        String exit_message = "Aight. Imma head out.\n"
                   + line;
        System.out.println(exit_message);
    }

}

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String logo = " ____ \n"
                    + "|  _ \\   ___   ___ \n"
                    + "| |_| | / _ \\ / _ \\\n"
                    + "| |_| | | __/ | __/\n"
                    + "|____/  \\___| \\___|\n";

        String msg = "------------------------------------------------ \n"
                + "Hello! I'm Bee! \n"
                + "What can I do for you? \n"
                + "------------------------------------------------";

        System.out.println(logo + "\n" + msg);

        boolean output = true;
        String input;

        while (output) {
            System.out.println(" ");
            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                output = false;
            } else {
                System.out.println("------------------------------------------------");
                System.out.println(input);
                System.out.println("------------------------------------------------");
            }
        }
    }
}

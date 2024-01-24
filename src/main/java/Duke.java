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
        String[] list = new String[100];
        int index = 0;

        while (output) {
            System.out.println(" ");
            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("------------------------------------------------");
                output = false;
            } else if (input.equals("list")) {
                System.out.println("------------------------------------------------");
                for (int i = 0; i < index; i++) {
                    System.out.println(i + 1 + ". " + list[i]);
                }
                System.out.println("------------------------------------------------");
            } else {
                System.out.println("------------------------------------------------");
                System.out.println("added: " + input);
                System.out.println("------------------------------------------------");
                list[index] = input;
                index += 1;
            }
        }
    }
}

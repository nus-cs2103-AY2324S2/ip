import java.util.Scanner;
public class Duke {
    public static void drawLine() {
        /**
         * Print out a line on the screen
         */
        System.out.println("-----------------------------------------------------");
    }

    public static void startChat() {
        /**
         * Provide commands to communicate with chatbot
         */
        drawLine();
        System.out.println("Hello! I'm Colin");
        System.out.println("What can I do for you?");
        drawLine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                drawLine();
                System.out.println("Bye. Hope to see you again soon!");
                drawLine();
                break;
            } else {
                drawLine();
                System.out.println(command);
                drawLine();
            }
        }
    }


    public static void main(String[] args) {
        startChat();
    }
}

import java.util.Scanner;

public class Duke {
    private static final String NAME = "Fatnom";
    private static final int lineLength = 60;

    public static void printLine() {
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    public static void printMessage(String message) {
        Duke.printLine();
        System.out.println(message);
        Duke.printLine();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String welcomeMessage = "hello! i'm " + NAME + ", your personal chatbot!\n" + "how can i help you today?";
        Duke.printMessage(welcomeMessage);

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                Duke.printMessage("bye! come visit me again!");
                break;
            } else {
                Duke.printMessage(input);
            }
        }
        sc.close();
    }
}

import java.util.Scanner;

public class Duke {
    public static String welcome = "   Hi, I'm Gronk! \uD83D\uDDFF \n"
            + "   What are we up to today?";

    public static String goodbye = "   System closing. Goodbye!";

    public static void lines() {
        System.out.println("  ----------------------------------------");
    }

    public static void printMessage(String message) {
        System.out.println(message);
        lines();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = "";
        lines();
        printMessage(welcome);
        String[] tasks = new String[100];
        int i = 0;
        while (true) {
            message = sc.nextLine();
            lines();
            if (message.equals("bye")) {
                break;
            } else if (message.equals("list")) {
                if (i == 0) {
                    System.out.println("   nothing added yet!");
                } else {
                    for (int j = 0; j < i; j++) {
                        System.out.println("   " + Integer.toString(j + 1) + ". " + tasks[j]);
                    }
                }
                lines();
            } else {
                printMessage("   added: " + message);
                tasks[i] = message;
                i += 1;
            }
        }
        printMessage(goodbye);
    }
}

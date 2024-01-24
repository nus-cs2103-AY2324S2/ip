import java.util.Scanner;

public class Duke {
    public static String welcome = "Hi, I'm Gronk! \uD83D\uDDFF \n"
            + "What are we up to today?";

    public static String goodbye = "System closing. Goodbye!";

    public static void lines() {
        System.out.println("----------------------------------------");
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
        while (true) {
            message = sc.next();
            if (message.equals("bye")) {
                break;
            } else {
                printMessage(message);
            }
        }
        printMessage(goodbye);
    }
}

import java.util.Scanner;

public class Duke {

    private static final String spacer = "____________________________________________________________\n";

    private static void startupMessage() {
        String name = "CBBW";
        System.out.println(spacer + "Hello! I'm " + name 
                           + "\nWhat can I do for you?\n" + spacer);
    }

    private static void goodbyeMessage() {
        System.out.println(spacer + "Bye. Hope to see you again soon!\n" + spacer);
    }
    public static void main(String[] args) {
        startupMessage();
        Scanner s = new Scanner(System.in);
        while (true) {
            String echo = s.nextLine();
            if (echo.equals("bye")) {
                s.close();
                break;
            }
            System.out.println(spacer + echo + "\n" + spacer);
        }
        goodbyeMessage();
    }
}

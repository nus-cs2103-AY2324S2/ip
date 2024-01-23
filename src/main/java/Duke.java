import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String spacer = "____________________________________________________________\n";
    private static ArrayList<String> toDoList = new ArrayList<>();

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

            if (echo.equals("list")) {
                System.out.println(spacer);
                for (int i = 1; i <= toDoList.size(); i++) {
                    System.out.println(i + "." + toDoList.get(i - 1) + "\n");
                }
                System.out.println(spacer);
                continue;
            }

            toDoList.add(echo);
            System.out.println(spacer + "added: " + echo + "\n" + spacer);
        }
        goodbyeMessage();
    }
}

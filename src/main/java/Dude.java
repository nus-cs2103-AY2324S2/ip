import java.util.Scanner;

public class Dude {
    static final String spacer = "____________________________________________________________\n";
    static final String name = "Dude";

    private static String getGreeting() {
        return spacer + "Hello! I'm Dude\nWhat can I do for you?\n" + spacer;
    }
    private static String getGoodbye() {
        return spacer + "Bye. Hope to see you again soon!\n" + spacer;
    }

    private static void echo(String input) {
        System.out.println(input);
    }
    public static void main(String[] args) {
        System.out.println(getGreeting());

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input == "bye") {
                break;
            } else {
                echo(input);
            }
        }
        System.out.println(getGoodbye());
    }
}

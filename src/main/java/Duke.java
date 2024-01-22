import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = "____________________________________________________________\n" +
        "Hello! I'm Jux\n" +
        "What can I do for you?\n";
        String end = "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________"
        ;
        System.out.println(message);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println("You entered: " + input);
        }
        System.out.println(end);


    }
}

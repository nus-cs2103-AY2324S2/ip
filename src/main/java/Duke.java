import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "_____________________________________________________\n";

        String greeting = horizontalLine + "Hello! I'm KwunBot!\nWhat can I do for you?\n" + horizontalLine;
        System.out.println(greeting);

        String goodbye = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;

        while (true) {
            Scanner sc = new Scanner(System.in);
            String req = sc.nextLine(); // Read user input

            if (req.equals("bye")) {
                System.out.println(goodbye);
                break;
            }
            System.out.println(horizontalLine + req + "\n" + horizontalLine);
        }
    }
}
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void exitMessage() {
        String divider = "-----------------------------------------------------";
        System.out.println(divider);
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }

    public static void greeting() {
        String divider = "-----------------------------------------------------";
        System.out.println(divider);
        String welcomeMessage = "Hello! I'm ShaunBot";
        String greet = "What can I do for you?";
        System.out.println(welcomeMessage);
        System.out.println(greet);

    }
    public static void main(String[] args) {
        ArrayList<String> al =new ArrayList<>();
        String divider = "-----------------------------------------------------";
        Duke.greeting();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } if (input.equals("list")) {
                System.out.println(divider);
                for (int i = 0; i < al.size(); i++) {
                    System.out.println(i+1 + ". " + al.get(i));
                }
                System.out.println(divider);
            }
            else {
                al.add(input);
                System.out.println(divider);
                System.out.println("added: " + input);
                System.out.println(divider);
            }
        }
        Duke.exitMessage();
    }
}

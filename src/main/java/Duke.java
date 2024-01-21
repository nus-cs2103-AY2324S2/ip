import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greetingMsg = "Hello! I'm PingMeBot\n" + "What can I do for you?";
        String exitMsg = "Bye. Hope to see you again soon!";
        System.out.println(greetingMsg);

        while (true) {
            String userInput = sc.nextLine();
            if (!userInput.equals("bye")) {
                System.out.println("\n" + userInput);
            } else {
                System.out.println("\n" + exitMsg);
                break;
            }
        }
    }
}

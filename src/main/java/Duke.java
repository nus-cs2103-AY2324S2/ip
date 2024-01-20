import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String welcome = "Hello! I'm TalkingBot\nWhat can I do for you?";
        System.out.println(welcome);

        Scanner scanner = new Scanner(System.in);
        boolean continueIter = true;


        while (continueIter) {
            String curCommand = scanner.nextLine();
            switch (curCommand) {
                case "bye":
                    continueIter = false;
                    break;
                default:
                    System.out.println(curCommand);
            }
        }

        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
        scanner.close();
    }
}

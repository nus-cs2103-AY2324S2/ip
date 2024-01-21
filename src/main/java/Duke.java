import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int index = 0;
        String greetingMsg = "Hello! I'm PingMeBot\n" + "What can I do for you?";
        String exitMsg = "Bye. Hope to see you again soon!";
        System.out.println(greetingMsg);

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("\n" + exitMsg);
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    int indexToPrint = i + 1;
                    System.out.println(indexToPrint + ". " + tasks[i]);
                }
            } else {
                System.out.println("\n" + "added: " + userInput);
                tasks[index] = userInput;
                index ++;
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        ArrayList<String> taskList = new ArrayList<>();

        System.out.println("Hello! I'm Bert!");
        System.out.println("What can I do for you?");

        do {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + "." + taskList.get(i));
                }
            } else {
                taskList.add(userInput);
                System.out.println("added: " + userInput);
            }
        } while (true);

        System.out.println("Bye! Hope to see you again!");
        scanner.close();
    }
}

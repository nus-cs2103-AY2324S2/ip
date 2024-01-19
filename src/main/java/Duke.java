import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm fakegpt\nWhat can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String userInput = scanner.nextLine();

        // while not exit
        while (!userInput.toLowerCase().equals("bye")) {

            //print list
            if (userInput.toLowerCase().equals("list")) {
                System.out.print(taskList);
            } else {
                taskList.add(userInput);
            }

            userInput = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}

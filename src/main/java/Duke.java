import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm fakegpt\nWhat can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String userInput = scanner.nextLine();

        while (!userInput.toLowerCase().equals("bye")) {
            if (userInput.toLowerCase().equals("list")) { //print list command
                System.out.print(taskList);

            } else if (userInput.split(" ").length == 2 && userInput.toLowerCase().startsWith("mark")) { //mark command
                int taskId = Integer.parseInt(userInput.split(" ")[1]);
                Task done = taskList.getTask(taskId);
                done.setDone();

            } else if (userInput.split(" ").length == 2 && userInput.toLowerCase().startsWith("unmark")) { //unmark comma
                int taskId = Integer.parseInt(userInput.split(" ")[1]);
                Task notDone = taskList.getTask(taskId);
                notDone.setNotDone();

            } else { // add task to list
                taskList.add(userInput);
            }

            userInput = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}

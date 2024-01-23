import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static void main(String[] args) {
        String line = "__________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Floofy");
        System.out.println("What can I do for you?");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        loop:
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.startsWith("mark")) {
                System.out.println(line);
                int idx = Integer.parseInt(userInput.substring(5));
                list.get(idx - 1).markTask();
                System.out.println(line);
            } else if (userInput.startsWith("unmark")) {
                System.out.println(line);
                int idx = Integer.parseInt(userInput.substring(7));
                list.get(idx - 1).unmarkTask();
                System.out.println(line);
            } else {
                switch (userInput) {
                    case "bye":
                        System.out.println(line);
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println(line);
                        scanner.close();
                        break loop;
                    case "list":
                        System.out.println(line);
                        for (int i = 0; i < list.size(); i++) {
                            String numberedOutput = String.format("%d. %s", i + 1, list.get(i).toString());
                            System.out.println(numberedOutput);
                        }
                        System.out.println(line);
                        break;
                    default:
                        Task newTask = new Task(userInput);
                        list.add(newTask);
                        System.out.println(line);
                        System.out.println("added: " + userInput);
                        System.out.println(line);
                }
            }
        }
    }
}

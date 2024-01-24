import java.util.Scanner;

public class Jayne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dash = "___________________________________";
        System.out.println(dash);
        System.out.println("Hello, I'm Jayne");
        System.out.println("What can I do for you?\n" + dash);
        TaskList taskList = new TaskList();

        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new JayneException("Input cannot be empty.");
                }

                String[] parts = input.split(" ", 2);

                // Handle different cases
                switch (parts[0].toLowerCase()) {
                    case "bye":
                        Handler.handleBye(dash);
                        return; // Exit the program
                    case "list":
                        Handler.handleList(taskList, dash);
                        break;
                    case "mark":
                        Handler.handleMark(parts, taskList, dash);
                        break;
                    case "unmark":
                        Handler.handleUnmark(parts, taskList, dash);
                        break;
                    case "todo":
                        Handler.handleTodo(parts, taskList, dash);
                        break;
                    case "deadline":
                        Handler.handleDeadline(parts, taskList, dash);
                        break;
                    case "event":
                        Handler.handleEvent(parts, taskList, dash);
                        break;
                    case "delete":
                        Handler.handleDelete(parts, taskList, dash);
                        break;
                    default:
                        throw new JayneException("What are you typing. please include either bye, list, mark, umark, todo, deadline or event in your inputs please");
                }
            } catch (JayneException e) {
                System.out.println(dash + "\nHuh?!?!? " + e.getMessage() + "\n" + dash);
            }
        }
    }
}

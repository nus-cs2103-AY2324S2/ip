import java.util.*;

public class William {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Task> tasks = new ArrayList<Task>();
        String logo = "William";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?\n");

        while (true) {
            String input = sc.nextLine();
            String[] texts = Methods.retrieveTexts(input);
            Commands command = Methods.retrieveCommand(texts[0]);

            switch (command) {
                case todo:
                    Methods.addTask(new Todo(texts[1]), tasks);
                    break;
                case deadline:
                    String[] deadlineDetails = Methods.splitBy(texts[1]);
                    Methods.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]), tasks);
                    break;
                case event:
                    String[] eventDetails = Methods.splitToAndFrom(texts[1]);
                    Methods.addTask(new Event(eventDetails[0], eventDetails[1], eventDetails[2]), tasks);
                    break;
                case list:
                    System.out.println("Here are the tasks in your list: ");
                    Methods.printList(tasks);
                    break;
                case mark:
                    System.out.println("Nice! I've marked this task as done:");
                    Methods.markAndUnmark(texts[1], tasks);
                    break;
                case unmark:
                    System.out.println("OK, I've marked this task as not done yet:");
                    Methods.markAndUnmark(texts[1], tasks);
                    break;
                case bye:
                    System.out.println("Bye. Hope to see you again soon!");
                    sc.close();
                    return;
                default:
                    System.out.println("Unknown command, please try again!");
            }

        }
    }
}

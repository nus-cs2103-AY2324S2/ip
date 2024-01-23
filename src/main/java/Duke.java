import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Hello! My name is Mitsuki, nice to meet you!\n" + "What can I do for you today?\n");


        ArrayList<Task> list = new ArrayList<Task>();
        String command = "nil";

        while (!command.equals(null)) {
            command = scan.next();
            switch (command) {
                case "deadline":
                    String fullString = scan.nextLine();
                    String[] tokens = fullString.split("/");
                    String description = tokens[0];
                    String trimmed = description.trim();
                    String by = tokens[1];
                    Task task = new Deadline(trimmed, by);
                    list.add(task);
                    System.out.println("OK, I have added the task '" + trimmed + "' to your list! :)");
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                case "todo":
                    String description1 = scan.nextLine();
                    String trimmed1 = description1.trim();
                    Task task1 = new Todo(trimmed1);
                    list.add(task1);
                    System.out.println("OK, I have added the task '" + trimmed1 + "' to your list! :)");
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                case "event":
                    String fullString1 = scan.nextLine();
                    String[] tokens1 = fullString1.split("/");
                    String description2 = tokens1[0];
                    String trimmed2 = description2.trim();
                    String from = tokens1[1];
                    String to = tokens1[2];
                    String toTrimmed = to.trim();
                    Task task2 = new Event(trimmed2, from, toTrimmed);
                    list.add(task2);
                    System.out.println("OK, I have added the task '" + trimmed2 + "' to your list! :)");
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                case "list":
                    System.out.println("Here are the items in your list:");
                    int i = 0;
                    int j = 1;
                    while (i < list.size()) {
                        System.out.println(j + ". " + list.get(i).toString());
                        i++;
                        j++;
                    }
                    break;
                case "mark":
                    int index = scan.nextInt();
                    list.get(index - 1).markAsDone();
                    System.out.println("Ok, I have marked this task as done. :D\n" + list.get(index - 1).toString());
                    break;
                case "unmark":
                    int index1 = scan.nextInt();
                    list.get(index1 - 1).markAsNotDone();
                    System.out.println("Ok, I have marked this task as not done yet. :O\n" + list.get(index1 - 1).toString());
                    break;
                case "bye":
                    System.out.println("Bye! Hope to see you again soon!\n");
                    scan.close();
                    System.exit(0);
                default:
                    break;
            }
        }



    }
}

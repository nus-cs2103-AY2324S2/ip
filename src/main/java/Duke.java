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
                case "add":
                    String description = scan.nextLine();
                    String trimmed = description.trim();
                    Task task = new Task(trimmed);
                    list.add(task);
                    System.out.println("OK, I have added the task '" + trimmed + "' to your list! :)");
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
                    System.exit(0);
                default:
                    break;
            }
        }
        


    }
}

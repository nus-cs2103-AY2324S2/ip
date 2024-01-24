import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {

        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */

        String lineBreak = "\t\t------------------------------------------";
        String options = "\t\t1. To create a 'To Do': todo <description>" +
                "\n\t\t2. To create a 'Deadline': deadline <description> /by <by>" +
                "\n\t\t3. To create a 'Event': event <description> /from <from> /to <to>" +
                "\n\t\t4. To mark task as done: mark <item number in list>" +
                "\n\t\t5. To mark task as undone: unmark <item number in list>" +
                "\n\t\t6. To exit: bye";

        System.out.println(lineBreak);
        System.out.println("\t\tHello! I'm Duchess");
        System.out.println("\t\t--------------How to Use Me--------------");
        System.out.println(options);
        System.out.println(lineBreak);

        Scanner scan = new Scanner( System.in );
        String input = scan.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(lineBreak);
                System.out.println("\t\tHere are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    Task t = list.get(i - 1);
                    System.out.println("\t\t" + i + "." + t);
                }
                System.out.println(lineBreak);
            }
            else if (input.contains("todo")) {
                System.out.println(lineBreak);
                String[] shortened_input = input.split("todo ");
                Task t = new ToDo(shortened_input[1]);
                list.add(t);
                System.out.println("\t\tGot it. I've added this task: \n\t\t  " + t);
                System.out.println("\t\tNow you have " + list.size() + " tasks in the list.");
                System.out.println(lineBreak);
            }
            else if (input.contains("deadline")) {
                System.out.println(lineBreak);
                String[] shortened_input = input.split("deadline ");
                String[] inputArray = shortened_input[1].split(" /by ");
                Task t = new Deadline(inputArray[0], inputArray[1]);
                list.add(t);
                System.out.println("\t\tGot it. I've added this task: \n\t\t  " + t);
                System.out.println("\t\tNow you have " + list.size() + " tasks in the list.");
                System.out.println(lineBreak);
            }
            else if (input.contains("event")) {
                System.out.println(lineBreak);
                String[] shortened_input = input.split("event ");
                String[] inputArray = shortened_input[1].split(" /from ");
                String[] from_to = inputArray[1].split(" /to ");
                Task t = new Event(inputArray[0], from_to[0], from_to[1]);
                list.add(t);
                System.out.println("\t\tGot it. I've added this task: \n\t\t  " + t);
                System.out.println("\t\tNow you have " + list.size() + " tasks in the list.");
                System.out.println(lineBreak);
            }
            else if (input.contains("unmark")) {
                System.out.println(input.charAt(7));
                int item_index = Character.getNumericValue(input.charAt(7));
                Task t = list.get(item_index-1);
                t.markAsUndone();
                System.out.println(lineBreak);
                System.out.println("\t\tOK, I've marked this task as not done yet:");
                System.out.println("\t\t  " + t);
                System.out.println(lineBreak);
            }
            else if (input.contains("mark")) {
                int item_index = Character.getNumericValue(input.charAt(5));
                Task t = list.get(item_index-1);
                t.markAsDone();
                System.out.println(lineBreak);
                System.out.println("\t\tNice! I've marked this task as done:");
                System.out.println("\t\t  " + t);
                System.out.println(lineBreak);
            }
            else {
                System.out.println("\t\t--------------How to Use Me--------------");
                System.out.println(options);
                System.out.println(lineBreak);
            }
            input = scan.nextLine();
        }

        System.out.println(lineBreak);
        System.out.println("\t\tBye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;


public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Taro\n"
                + "What can I do for you?\n";

        System.out.println(greeting);

        Scanner s = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String input = s.nextLine();
            String[] words = input.split("\\s+");
            String command = words[0];

            if (command.equals("bye")) {
                break;

            } else if (command.equals("list")) {
                int num = 1;
                Iterator<Task> it = list.iterator();
                System.out.println("Here are the tasks in the list:");
                while (it.hasNext()) {
                    System.out.println(num + "." + it.next().toString());
                    num++;
                }

            } else if (command.equals("mark")) {
                int index = Integer.parseInt(words[1]);
                list.get(index-1).markAsDone();

            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(words[1]);
                list.get(index-1).markAsUndone();

            } else if (command.equals("todo")) {
                String description = input.trim().substring("todo".length()).trim();
                list.add(new ToDo(description));
                String task = list.get(list.size() - 1).toString();
                String numberOfTasks = "Now you have "
                        + String.valueOf(list.size())
                        + " task(s) left";
                System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);

            } else if (command.equals("deadline")) {
                String[] parts = input.split("/by");
                String description = parts[0].trim().substring("deadline".length()).trim();
                String deadline = parts[1];
                list.add(new Deadline(description, deadline));
                String task = list.get(list.size() - 1).toString();
                String numberOfTasks = "Now you have "
                        + String.valueOf(list.size())
                        + " task(s) left";
                System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);

            } else if (command.equals("event")) {
                String[] parts = input.split("/from| /to");
                String description = parts[0].trim().substring("event".length()).trim();
                String start = parts[1];
                String end = parts[2];
                list.add(new Event(description, start, end));
                String task = list.get(list.size() - 1).toString();
                String numberOfTasks = "Now you have "
                        + String.valueOf(list.size())
                        + " task(s) left";
                System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);
            }
        }
        s.close();
        System.out.println("BYE BYE");
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> items = new ArrayList<>();
        int itemCount = 0;
        boolean isValid = true;

        System.out.println("Hello! I'm GuanGuanBot!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (isValid) {
            String command = scanner.nextLine();
            try {
                Pair<Boolean, Integer> pair = handleInput(command, items, itemCount);
                isValid = pair.first();
                itemCount = pair.second();
            } catch (DukeException e) {
                System.out.printf("[!] %s\n", e.getMessage());
            }
            System.out.println();
        }
    }

    private static Pair<Boolean, Integer> handleInput(String command, ArrayList<Task> items, int itemCount) throws DukeException {
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return Pair.of(false, itemCount);
        } else if (command.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i=0; i<itemCount; i++) {
                System.out.printf("%s. %s%n", i+1, items.get(i));
            }
        } else if (command.startsWith("mark")) {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            items.get(index).markDone();

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(items.get(index));

        } else if (command.startsWith("unmark")) {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            items.get(index).unmarkDone();

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(items.get(index));
        }

        else if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }

            System.out.println("Got it. I've added this task:");

            Todo todo = new Todo(command.substring(5, command.length()));

            items.add(todo);
            itemCount++;

            System.out.println(todo);
            System.out.printf("Now you have %s tasks in the list.%n", itemCount);

        } else if (command.startsWith("deadline")) {
            if (command.length() <= 9) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }

            System.out.println("Got it. I've added this task:");

            String[] splittedCommand = command.split(" /by ");
            String task = splittedCommand[0].substring(9, splittedCommand[0].length());
            String by = splittedCommand[1];

            Deadline deadline = new Deadline(task, by);
            items.add(deadline);
            itemCount++;

            System.out.println(deadline);
            System.out.printf("Now you have %s tasks in the list.%n", itemCount);

        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }

            System.out.println("Got it. I've added this task:");

            String[] splittedCommand = command.split(" /from ");
            String task = splittedCommand[0].substring(6, splittedCommand[0].length());

            String[] splittedTime = splittedCommand[1].split(" /to ");
            String from = splittedTime[0];
            String to = splittedTime[1];

            Event event = new Event(task, from, to);
            items.add(event);
            itemCount++;

            System.out.println(event);
            System.out.printf("Now you have %s tasks in the list.%n", itemCount);
        } else if (command.startsWith("delete")) {
            if (command.length() <= 7) {
                throw new DukeException("OOPS!!! Task ID cannot be empty.");
            }

            System.out.println("Noted. I've removed this task:");

            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = items.remove(index);
            itemCount -= 1;
            System.out.println(task);

            System.out.printf("Now you have %s tasks in the list.%n", itemCount);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return Pair.of(true, itemCount);
    }
}
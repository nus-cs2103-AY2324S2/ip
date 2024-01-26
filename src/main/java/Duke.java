import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        Scanner scanner = new Scanner(System.in);

        Storage storage = new Storage("./data/test.txt");
        ArrayList<Task> items = storage.readData();
        boolean isValid = true;

        System.out.println("Hello! I'm GuanGuanBot!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (isValid) {
            String command = scanner.nextLine();
            try {
                isValid = handleInput(command, items);
                storage.saveData(items);
            } catch (DukeException e) {
                System.out.printf("[!] %s\n", e.getMessage());
            }
            System.out.println();
        }
    }

    private static boolean handleInput(String command, ArrayList<Task> items) throws DukeException {
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        } else if (command.equals("list")) {
            if (items.isEmpty()) {
                System.out.println("You have no tasking available.");
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < items.size(); i++) {
                    System.out.printf("%s. %s%n", i + 1, items.get(i));
                }
            }
        } else if (command.startsWith("mark")) {
            try {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                items.get(index).markDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(items.get(index));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid task ID to mark");
            }

        } else if (command.startsWith("unmark")) {
            try {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                items.get(index).unmarkDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(items.get(index));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid task ID to unmark");
            }
        }

        else if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }

            System.out.println("Got it. I've added this task:");

            Todo todo = new Todo(command.substring(5));

            items.add(todo);

            System.out.println(todo);
            System.out.printf("Now you have %s tasks in the list.%n", items.size());

        } else if (command.startsWith("deadline")) {
            if (command.length() <= 9) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }

            try{
                String[] splittedCommand = command.split(" /by ");
                String task = splittedCommand[0].substring(9);
                String by = splittedCommand[1];

                Deadline deadline = new Deadline(task, Utils.convertStringToDateTime(by));
                items.add(deadline);

                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.printf("Now you have %s tasks in the list.%n", items.size());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid deadline date. Use /by");
            }

        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }

            try {
                String[] splittedCommand = command.split(" /from ");
                String task = splittedCommand[0].substring(6);

                String[] splittedTime = splittedCommand[1].split(" /to ");
                String from = splittedTime[0];
                String to = splittedTime[1];

                Event event = new Event(task, Utils.convertStringToDateTime(from), Utils.convertStringToDateTime(to));
                items.add(event);

                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.printf("Now you have %s tasks in the list.%n", items.size());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid event date. Use /from and /to");
            }

        } else if (command.startsWith("delete")) {
            if (command.length() <= 7) {
                throw new DukeException("OOPS!!! Task ID cannot be empty.");
            }

            System.out.println("Noted. I've removed this task:");

            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = items.remove(index);
            System.out.println(task);

            System.out.printf("Now you have %s tasks in the list.%n", items.size());
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }
}
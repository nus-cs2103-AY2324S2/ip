import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Linus!\nWhat can I do for you?\n\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // while loop to repeat printing of multiple Scanner inputs
        // adapted with help of AI
        while (true) {
            String input = sc.nextLine();

            try {
                // Check if the user wants to exit.
                // When comparing strings for equality, you should use the equals() method, not the == operator.
                if (input.equals("bye")) { // exit chat
                    System.out.println("Bye. It's been a pleasure chatting with you!");
                    break;
                } else if (input.equals("list")) { // list tasks
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 0; i < tasks.size(); i++) {
                        Task currTask = tasks.get(i);
                        System.out.println((i + 1) + ". " + currTask);
                    }
                } else if (input.startsWith("mark")) {
                    Integer indexOfTask = Integer.parseInt(input.substring(5));
                    Task currTask = tasks.get(indexOfTask);
                    currTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currTask);
                } else if (input.startsWith("unmark")) {
                    Integer indexOfTask = Integer.parseInt(input.substring(7));
                    Task currTask = tasks.get(indexOfTask);
                    currTask.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(currTask);
                } else if (input.startsWith("todo")) {
                    // Check if the input string is long enough
                    if (input.length() <= 5) {
                        throw new DukeException("Please specify the description of the todo task, " +
                                "starting from one whitespace away from the keyword 'todo'" +
                                " (e.g. todo borrow book)");
                    }

                    String description = input.substring(5);

                    if (description.isEmpty()) {
                        throw new DukeException("Please specify the description of the todo task, " +
                                "starting from one whitespace away from the keyword 'todo'" +
                                " (e.g. todo borrow book)");
                    }

                    Task todo = new Todo(description);
                    tasks.add(todo);
                    System.out.println("Got it. I've added this task: \n" + todo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.startsWith("deadline")) {
                    String[] substrings = input.split(" /by ");

                    if (substrings[0].length() <= 9) {
                        throw new DukeException("Please specify the description of the todo task, " +
                                "starting from one whitespace away from the keyword 'todo'" +
                                " (e.g. todo borrow book)");
                    }

                    String description = substrings[0].substring(9);

                    if (description.isEmpty()) {
                        throw new DukeException("Please specify the description of the deadline task " +
                                "(e.g. deadline return book /by Sunday)");
                    }

                    if (!input.contains(" /by ") || substrings[1].isEmpty()) {
                        throw new DukeException("Please state the deadline period " +
                                "(e.g. deadline return book /by Sunday)");
                    }

                    String by = substrings[1];
                    Task deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    System.out.println("Got it. I've added this task: \n" + deadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.startsWith("event")) {
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new DukeException("Please state the event period by using " +
                                " /from and /to with correct spacing (eg. event team meeting /from Mon 2pm /to 4pm)");
                    }

                    String[] substrings = input.split(" /from ");
                    String description = substrings[0].substring(6);
                    String[] substrings2 = substrings[1].split(" /to ");

                    if (substrings2.length < 2 || description.isEmpty()) {
                        throw new DukeException("Please specify the description of the deadline task " +
                                "(eg. event team meeting /from Mon 2pm /to 4pm)");
                    }

                    String from = substrings2[0];
                    String to = substrings2[1];
                    Task event = new Event(description, from, to);
                    tasks.add(event);
                    System.out.println("Got it. I've added this task: \n" + event);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new DukeException("Please give commands that start with any of the following:" +
                            " [todo, deadline, event, mark, unmark, list, bye]");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

        // Close the scanner
        sc.close();
    }
}

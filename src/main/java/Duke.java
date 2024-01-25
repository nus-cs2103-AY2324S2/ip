import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static class EmptyTodoDescriptionException extends Exception {
        public EmptyTodoDescriptionException(String message) {
            super(message);
        }
    }

    static class UnknownCommandException extends Exception {
        public UnknownCommandException(String message) {
            super(message);
        }
    }

    public static void exitMessage() {
        String divider = "-----------------------------------------------------";
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }

    public static void greeting() {
        String divider = "-----------------------------------------------------";
        System.out.println(divider);
        System.out.println("Hello! I'm ShaunBot");
        System.out.println("What can I do for you?");
        System.out.println(divider);
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        greeting();
        Scanner sc = new Scanner(System.in);

        while (true) {

            try {
            String input = sc.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println("-----------------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i).toString());
                }
                System.out.println("-----------------------------------------------------");
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                tasks.get(index).markAsDone();
                System.out.println("-----------------------------------------------------");
                System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
                System.out.println("-----------------------------------------------------");
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                tasks.get(index).unmarkAsDone();
                System.out.println("-----------------------------------------------------");
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
                System.out.println("-----------------------------------------------------");
            } else if (command.equals("todo")) {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new EmptyTodoDescriptionException("OOPS!!! The description of a todo cannot be empty.");
                }

                Todo newTodo = new Todo(parts[1]);
                tasks.add(newTodo);
                System.out.println("-----------------------------------------------------");
                System.out.println("Got it. I've added this task:\n  " + newTodo);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("-----------------------------------------------------");
            } else if (command.equals("deadline")) {
                String[] deadlineParts = parts[1].split(" /by ");
                Deadline newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                tasks.add(newDeadline);
                System.out.println("-----------------------------------------------------");
                System.out.println("Got it. I've added this task:\n  " + newDeadline);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("-----------------------------------------------------");
            } else if (command.equals("event")) {
                String[] eventParts = parts[1].split(" /from ");
                String[] timeParts = eventParts[1].split(" /to ");
                Event newEvent = new Event(eventParts[0], timeParts[0], timeParts[1]);
                tasks.add(newEvent);
                System.out.println("-----------------------------------------------------");
                System.out.println("Got it. I've added this task:\n  " + newEvent);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("-----------------------------------------------------");
            }

            else if (command.equals("delete")) {
                int indexToDelete = Integer.parseInt(parts[1]) - 1;
                if (indexToDelete < 0 || indexToDelete >= tasks.size()) {
                    throw new UnknownCommandException("Task number out of range.");
                }
                Task removedTask = tasks.remove(indexToDelete);
                System.out.println("-----------------------------------------------------");
                System.out.println("Noted. I've removed this task:\n  " + removedTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("-----------------------------------------------------");
            }


            else {
                throw new UnknownCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (EmptyTodoDescriptionException | UnknownCommandException e) {
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        } catch (NumberFormatException e) {
            System.out.println("-----------------------------------------------------");
            System.out.println("OOPS!!! The task number provided is invalid.");
            System.out.println("-----------------------------------------------------");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("-----------------------------------------------------");
            System.out.println("OOPS!!! The task number is out of range.");
            System.out.println("-----------------------------------------------------");
        }
        }
        exitMessage();
        sc.close();
    }
}


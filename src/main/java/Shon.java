import java.util.Scanner;

public class Shon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TodoList list = new TodoList();

        print("Hello! I'm Shon", "What can I do for you?");
        String input = scanner.nextLine();
        input = input.strip();
        while (!input.equals("bye")) {
            String action = input.split(" ")[0];
            try {
                switch (action) {
                    case "":
                        print("Please enter a command.");
                        break;
                    case "list":
                        print(list.getList());
                        break;
                    case "mark":
                        print(markTask(input, list));
                        break;
                    case "unmark":
                        print(unmarkTask(input, list));
                        break;
                    case "todo":
                        print(addTodo(input, list));
                        break;
                    case "deadline":
                        print(addDeadline(input, list));
                        break;
                    case "event":
                        print(addEvent(input, list));
                        break;
                    case "delete":
                        print(deleteEvent(input, list));
                        break;
                    default:
                        print("OOPS!!! I'm sorry, but I don't know what that means :-)");
                }
            } catch (ParameterException e) {
                print(e.getMessage());
            }

            input = scanner.nextLine();
            input = input.strip();
        }
        scanner.close();
        print("Bye. Hope to see you again soon!");
    }

    private static void print(String... messages) {
        String line = "    _______________" +
                "_____________________________________________";
        System.out.println(line);
        for (String msg : messages) {
            System.out.println("     " + msg);
        }
        System.out.println(line);
        System.out.println();
    }

    private static String[] markTask(String input, TodoList list) throws ParameterException {
        return list.mark(getIndex(input));
    }

    private static String[] unmarkTask(String input, TodoList list) throws ParameterException {
        return list.unmark(getIndex(input));
    }

    private static int getIndex(String input) throws ParameterException {
        String[] split = input.split(" ");
        if (split.length == 1) {
            throw new ParameterException("Please enter which task number to mark.");
        }
        if (split.length > 2) {
            throw new ParameterException("Please enter only one task number to mark.");
        }
        try {
            return Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            throw new ParameterException("Please enter a number for task number.");
        }
    }

    private static String[] addTodo(String input, TodoList list) throws ParameterException {
        String[] split = input.split(" ", 2);
        if (split.length == 1) {
            throw new ParameterException("Todo description cannot be empty.");
        }
        String description = split[1];
        return list.addTodo(description);
    }

    private static String[] addDeadline(String input, TodoList list) throws ParameterException {
        String[] split = input.split(" ", 2);
        if (split.length == 1) {
            throw new ParameterException("Please enter Deadline description and due date/time.");
        }
        if (!split[1].contains("/by")) {
            throw new ParameterException("Please indicate due date/time after \"/by\".");
        }
        if (split[1].strip().startsWith("/by")) {
            throw new ParameterException("Deadline description cannot be empty.");
        }
        if (split[1].strip().endsWith("/by")) {
            throw new ParameterException("Deadline due date/time cannot be empty.");
        }
        String[] task = split[1].split("/by", 2);
        return list.addDeadline(task[0].strip(), task[1].strip());
    }

    private static String[] addEvent(String input, TodoList list) throws ParameterException {
        String[] split = input.split(" ", 2);
        if (split.length == 1) {
            throw new ParameterException("Please enter Event description and from/to date/time.");
        }
        if (!split[1].contains("/from")) {
            throw new ParameterException("Please indicate from date/time after \"/from\".");
        }
        if (!split[1].contains("/to")) {
            throw new ParameterException("Please indicate to date/time after \"/to\".");
        }
        if (split[1].strip().startsWith("/from")) {
            throw new ParameterException("Event description cannot be empty.");
        }
        if (split[1].strip().endsWith("/to")) {
            throw new ParameterException("Event to date/time cannot be empty.");
        }
        String[] task = split[1].strip().split("/from", 2);
        String description = task[0].strip();
        if (!task[1].contains("/to")) {
            throw new ParameterException("\"/to\" must come after \"/from\".");
        }
        if (task[1].strip().startsWith("/to")) {
            throw new ParameterException("Event from date/time cannot be empty.");
        }
        String[] datetimes = task[1].split("/to", 2);
        return list.addEvent(description, datetimes[0].strip(), datetimes[1].strip());
    }

    private static String[] deleteEvent(String input, TodoList list) throws ParameterException {
        return list.deleteTask(getIndex(input));
    }
}

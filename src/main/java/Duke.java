import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String botName = "KokBot";
        ArrayList<Task> tasks = new ArrayList<Task>();

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        welcome(botName);
        Scanner scanner = new Scanner(System.in);
        lineBreak();
        String input = scanner.nextLine();
        while (true) {
            try {
                if (input.equals("bye")) {
                    break;
                }
                String[] parts = input.split(" ");
                switch (parts[0]) {
                    case "list":
                        printList(tasks);
                        break;
                    case "mark":
                        int toMark = Integer.parseInt(parts[1]) - 1;
                        markTask(tasks.get(toMark));
                        break;
                    case "unmark":
                        int toUnmark = Integer.parseInt(parts[1]) - 1;
                        unmarkTask(tasks.get(toUnmark));
                        break;
                    case "todo":
                        tasks.add(createTodo(input));
                        numList(tasks.size());
                        break;
                    case "deadline":
                        tasks.add(createDeadline(parts));
                        numList(tasks.size());
                        break;
                    case "event":
                        tasks.add(createEvent(parts));
                        numList(tasks.size());
                        break;
                    case "delete":
                        deleteTask(parts, tasks);
                        numList(tasks.size());
                        break;
                    default:
                        throw new DukeException("Unknown command");
                }
            } catch (DukeException e) {
                lineBreak();
                System.out.println(e);
            }
            lineBreak();
            input = scanner.nextLine();
        }
        farewell();
    }

    public static void lineBreak() {
        System.out.println("____________________________________________________________\n");
    }

    public static void numList(int len) {
        System.out.println(String.format(" Now you have %d tasks in the list.", len));
    }

    public static void printList(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format(" %d. %s", i + 1, tasks.get(i)));
        }
    }

    public static void markTask(Task task) {
        task.markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(String.format(" %s", task));
    }

    public static void unmarkTask(Task task) {
        task.markAsUndone();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(String.format(" %s", task));
    }

    public static void welcome(String botName) {
        System.out.println(String.format("""
                ____________________________________________________________
                 Hello! I'm %s
                 What can I do for you?
                 """, botName));
    }

//    public static Task createTask(String text) {
//
//        System.out.println(String.format("""
//                ____________________________________________________________
//                 added: %s""", text));
//        return new Task(text);
//    }

    public static Todo createTodo(String input) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("Unknown usage - description should of \"todo\" should not be empty.");
        }
        Todo newTodo = new Todo(input.substring(5));
        System.out.println(String.format("""
                ____________________________________________________________
                 Got it. I've added this task:
                   %s""", newTodo));
        return newTodo;
    }

    public static Deadline createDeadline(String[] parts) throws DukeException{
        int i;
        for (i = 0; i < parts.length; i++) {
            if (parts[i].equals("/by")) {
                break;
            }
        }

        if (i == parts.length) {
            throw new DukeException("Unknown usage - /by not found in \"deadline\" command.");
        }

        String description = String.join(" ", Arrays.copyOfRange(parts, 1, i));
        String dueDate = String.join(" ", Arrays.copyOfRange(parts, i + 1, parts.length));

        if (description.equals("")) {
            throw new DukeException("Unknown usage - description of \"deadline\" should not be empty.");
        }
        if (dueDate.equals("")) {
            throw new DukeException("Unknown usage - due date of \"deadline\" should not be empty.");
        }

        Deadline newDeadline = new Deadline(description, dueDate);
        System.out.println(String.format("""
                ____________________________________________________________
                 Got it. I've added this task:
                   %s""", newDeadline));
        return newDeadline;
    }

    public static Event createEvent(String[] parts) throws DukeException{
        int fromIndex = -1;
        int toIndex = 0;

        //assume string always includes /from and /to
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("/from")) {
                fromIndex = i;
            } else if (parts[i].equals("/to")) {
                toIndex = i;
            }
        }

        if (fromIndex == -1) {
            throw new DukeException("Unknown usage - /from not found in \"event\" command.");
        }
        if (toIndex == -1) {
            throw new DukeException("Unknown usage - /to not found in \"event\" command.");
        }

        String description = String.join(" ", Arrays.copyOfRange(parts, 1, fromIndex));
        String startDate = String.join(" ", Arrays.copyOfRange(parts, fromIndex + 1, toIndex));
        String endDate = String.join(" ", Arrays.copyOfRange(parts, toIndex + 1, parts.length));

        if (description.equals("")) {
            throw new DukeException("Unknown usage - description of \"event\" should not be empty.");
        }
        if (startDate.equals("")) {
            throw new DukeException("Unknown usage - start date of \"event\" should not be empty.");
        }
        if (endDate.equals("")) {
            throw new DukeException("Unknown usage - end date of \"event\" should not be empty.");
        }

        Event newEvent = new Event(description, startDate, endDate);
        System.out.println(String.format("""
                ____________________________________________________________
                 Got it. I've added this task:
                   %s""", newEvent));
        return newEvent;
    }

    public static void deleteTask (String[] parts, ArrayList<Task> tasks) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("Unknown usage - task number should be included in \"delete\" command");
        }
        for (char c : parts[1].toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new DukeException("Unknown usage - task number given is not a valid number");
            }
        }
        int i = Integer.parseInt(parts[1]) - 1;
        Task removedTask = tasks.remove(i);
        System.out.println(String.format("""
                ____________________________________________________________
                 Noted. I've removed this task:
                   %s""", removedTask));
    }
    public static void farewell() {
        System.out.println("""
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                 """);
    }
}

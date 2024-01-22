import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String botName = "KokBot";
        Task[] tasks = new Task[100];

        int next = 0;
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
            if (input.equals("bye")){
                break;
            }
            String[] parts = input.split(" ");
            switch (parts[0]) {
                case "list":
                    printList(tasks, next);
                    break;
                case "mark":
                    int toMark = Integer.parseInt(parts[1])-1;
                    markTask(tasks[toMark]);
                    break;
                case "unmark":
                    int toUnmark = Integer.parseInt(parts[1])-1;
                    unmarkTask(tasks[toUnmark]);
                    break;
                case "todo":
                    tasks[next] = createTodo(input.substring(5));
                    next++;
                    numList(next);
                    break;
                case "deadline":
                    tasks[next] = createDeadline(parts);
                    next++;
                    numList(next);
                    break;
                case "event":
                    tasks[next] = createEvent(parts);
                    next++;
                    numList(next);
                    break;
                default:
                    tasks[next] = createTask(input);
                    next++;
                    break;
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
        System.out.println(String.format(" Now you have %d tasks in the list", len));
    }

    public static void printList(Task[] tasks, int next) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < next; i++) {
            System.out.println(String.format(" %d. %s", i+1, tasks[i]));
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

    public static Task createTask(String text) {

        System.out.println(String.format("""
                ____________________________________________________________
                 added: %s""", text));
        return new Task(text);
    }

    public static Todo createTodo(String description) {
        Todo newTodo = new Todo(description);
        System.out.println(String.format("""
                ____________________________________________________________
                 Got it. I've added this task:
                   %s""", newTodo));
        return newTodo;
    }

    public static Deadline createDeadline(String[] parts) {
        int i;
        for (i = 0; i < parts.length; i++) {
            if (parts[i].equals("/by")) {
                break;
            }
        }

        String description = String.join(" ", Arrays.copyOfRange(parts, 1, i));
        String dueDate = String.join(" ", Arrays.copyOfRange(parts, i + 1, parts.length));

        Deadline newDeadline = new Deadline(description, dueDate);
        System.out.println(String.format("""
                ____________________________________________________________
                 Got it. I've added this task:
                   %s""", newDeadline));
        return newDeadline;
    }

    public static Event createEvent(String[] parts) {
        int fromIndex = 0;
        int toIndex = 0;

        //assume string always includes /from and /to
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("/from")) {
                fromIndex = i;
            } else if (parts[i].equals("/to")) {
                toIndex = i;
            }
        }

        String description = String.join(" ", Arrays.copyOfRange(parts, 1, fromIndex));
        String startDate = String.join(" ", Arrays.copyOfRange(parts, fromIndex + 1, toIndex));
        String endDate = String.join(" ", Arrays.copyOfRange(parts, toIndex + 1, parts.length));

        Event newEvent = new Event(description, startDate, endDate);
        System.out.println(String.format("""
                ____________________________________________________________
                 Got it. I've added this task:
                   %s""", newEvent));
        return newEvent;
    }

    public static void farewell() {
        System.out.println("""
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                 """);
    }
}

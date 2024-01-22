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
                default:
                    tasks[next] = new Task(input);
                    next++;
                    echo(input);
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

    public static void printList(Task[] tasks, int next) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < next; i++) {
            System.out.println(String.format(" %d. %s", i+1, tasks[i].getDescription()));
        }
    }

    public static void markTask(Task task) {
        task.markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(String.format(" %s", task.getDescription()));
    }

    public static void unmarkTask(Task task) {
        task.markAsUndone();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(String.format(" %s", task.getDescription()));
    }

    public static void welcome(String botName) {
        System.out.println(String.format("""
                ____________________________________________________________
                 Hello! I'm %s
                 What can I do for you?
                 """, botName));
    }

    public static void echo(String text) {
        System.out.println(String.format("""
                ____________________________________________________________
                 added: %s""", text));
    }

    public static void farewell() {
        System.out.println("""
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                 """);
    }
}

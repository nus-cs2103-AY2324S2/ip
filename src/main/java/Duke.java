import java.util.Scanner;

public class Duke {
    private final static Task[] tasks =  new Task[100];
    private static int count = 0;
    public static void main(String[] args) {
        greetUser();
        echo();
        exit();
    }
    private static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Judy");
        System.out.println(" What can I do for you?");
        System.out.println("________________________________________");
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            command = scanner.nextLine();
            System.out.println("____________________________________");
            if(command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                listOutTasks();
            } else if (command.startsWith("mark")){
                markTask(command);
            } else if (command.startsWith("unmark")) {
                unmarkTask(command);
            } else {
                addTask(command);
            }
            System.out.println("____________________________________");
        } while (true);

        scanner.close();
    }

    private static void markTask(String command) {
        String[] words = command.split(" ");
        int taskId = Integer.parseInt(words[1]) -1;
        Task t = tasks[taskId];
        tasks[taskId].markAsDone();
        System.out.println("Nice ! I've marked this task as done: \n" + t.toString());
    }

    private static void unmarkTask(String command) {
        String[] words = command.split(" ");
        int taskId = Integer.parseInt(words[1]) - 1;
        Task t = tasks[taskId];
        t.markAsUndone();
        System.out.println("Ok, I've marked this task as not done yet: \n" + t.toString());
    }

    private static void addTask(String task) {
        Task t = new Task(task);
        tasks[count++] = t;
        System.out.println("added: " + task);
    }

    private static void listOutTasks() {
        if (count == 0) {
            System.out.println("No task added");
        } else {
            for(int i = 0; i < count; i++) {
                int id = i+1;
                Task t = tasks[i];
                System.out.println(id + ". " + t.toString());
            }
        }
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________");
    }
}

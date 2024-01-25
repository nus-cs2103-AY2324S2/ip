import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {

    public static void commandHandler(String divider) {
        Scanner scanner = new Scanner(System.in);
        String outro = "Bye. Hope to see you soon!";

        Boolean isExit = false;
        String command = "";

        List<Task> tasks = new ArrayList<Task>();

        while (!isExit){
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(divider);
                System.out.println("    "+outro);
                System.out.println(divider);
                isExit = true;
            } else if (command.equals("list")) {
                if (tasks.size() == 0) {
                    System.out.println("    Your list is empty at the moment !");
                } else {
                    System.out.println(divider);
                    System.out.println("    Here are the tasks in your list:");
                    for (Task task : tasks) {
                        int index = tasks.indexOf(task);
                        task.taskPrinter(index);
                    }
                    System.out.println(divider);
                }
            } else if (command.startsWith("mark ")) {
                // retrieve the index
                int index = Integer.parseInt(command.substring(5)) - 1;
                if (index >= 0 && index <= tasks.size()) {
                    Task curr = tasks.get(index);
                    curr.markAsDone();

                    System.out.println(divider);
                    System.out.println("    Nice! I've marked this task as done: ");
                    curr.taskPrinter(index);
                    System.out.println(divider);
                } else {
                    System.out.println("    Invalid number provided! Please give a valid index!");
                }
            } else if (command.startsWith("unmark ")) {
                // retrieve the index
                int index = Integer.parseInt(command.substring(7)) - 1;
                if (index >= 0 && index <= tasks.size()) {
                    Task curr = tasks.get(index);
                    curr.markAsUndone();

                    System.out.println(divider);
                    System.out.println("    OK, I've marked this task as not done yet: ");
                    curr.taskPrinter(index);
                    System.out.println(divider);
                } else {
                    System.out.println("    Invalid number provided! Please give a valid index!");
                }
            } else {
                Task newTask = new Task(command);
                tasks.add(newTask);
                System.out.println(divider);
                System.out.println("    added: " + command);
                System.out.println(divider);
            }
        }
    }

    public static void introPrinter(String botName, String divider) {
        System.out.println(divider);
        System.out.println("   Hello there! I'm " + botName);
        System.out.println("   What can I do for you today ?");
        System.out.println(divider);
    }

    public static void main(String[] args) {
        String botName = "TOBIAS";
        String divider = "  ---------------------------------------------------------------------------------------";
        introPrinter(botName, divider);
        commandHandler(divider);
    }
}

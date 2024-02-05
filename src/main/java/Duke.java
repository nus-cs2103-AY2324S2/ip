import java.util.Scanner;

public class Duke {
    private static final Tasks taskManager = new Tasks();
    public static void main(String[] args) {
        // Load tasks from file on startup
        //taskManager.loadTasks();
        greetUser();
        echo();
        exit();
    }
    private static void greetUser() {
        System.out.println("________________________________________");
        System.out.println(" Hello! I'm Judy");
        System.out.println(" What can I do for you?");
        System.out.println("________________________________________");
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            try {
                command = scanner.nextLine();
                System.out.println("____________________________________");
                if(command.equalsIgnoreCase("bye")) {
                    break;
                } else if (command.equals("list")) {
                    taskManager.listOutTasks();
                } else if (command.startsWith("mark")){
                    taskManager.markTask(command);
                } else if (command.startsWith("unmark")) {
                    taskManager.unmarkTask(command);
                } else if (command.startsWith("delete")) {
                    taskManager.deleteTask(command);
                } else {
                    taskManager.addTask(command);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("____________________________________");

        } while (true);

        //scanner.close();
    }



    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________");
    }
}

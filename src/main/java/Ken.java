import java.util.Scanner;
public class Ken {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;
    public static void main(String[] args) {
        //greet
        System.out.println("Hi Barbie!");
        System.out.println("I'm Ken!");

        System.out.println("What would you like to beach today?\n");

        //create scanner
        Scanner scanner = new Scanner(System.in);
        String command;

        //response
        do {
            command = scanner.nextLine();

            if (command.equals("list")) {
                listTasks();
            }
            else if (!command.equalsIgnoreCase("bye")) {
                addTask(command);
            }
            else {
            }

        } while (!command.equalsIgnoreCase("bye"));


        //byee
        System.out.println("Beach off!\n");

        scanner.close();
    }

    private static  void echoCommand(String command) {
        System.out.println(command);
    }

    private static  void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
            System.out.println("added task: " + task);
        } else {
            System.out.println("Way too many too many tasks for today Barbie!");
            System.out.println("Slow the Slayy\n");

        }
    }

    private static  void listTasks() {

        System.out.println("Hold my ice cream,");

        if (tasks.length == 1) {
            System.out.println("actually, wait, i'm taking my ice cream back");
            System.out.println("no tasks yet");
        } else {
            System.out.println("Your tasks for today: \n");

            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }
}

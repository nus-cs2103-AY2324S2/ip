import java.util.Scanner;
public class Duke {
    private static Task[] tasksArr = new Task[100];

    public static void main(String[] args) {

        String horiLine = "____________________________________________________________\n";

        String greet = "Hello! I'm Dino\n"
                + "What can I do for you?\n"
                + horiLine;
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();


        while(!input.equalsIgnoreCase("bye")) {
            System.out.println(horiLine);
            processInput(input);
            System.out.println(horiLine);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void processInput(String input) {
        String[] parsedInput = input.split(" ", 2);
        if (parsedInput[0].equalsIgnoreCase("list")) {
            listTasks();
        } else if (parsedInput[0].equalsIgnoreCase("mark")) {
            completeTask(parsedInput);
        } else if (parsedInput[0].equalsIgnoreCase("unmark")) {
            uncompleteTask(parsedInput);
        } else {
            addTask(parsedInput);
        }
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println("  " + (i + 1) + "." + tasksArr[i]);
        }
    }
    private static void addTask(String[] input) {
        if (input[0].equalsIgnoreCase("deadline")) {
            String[] parsedInput = input[1].split("/", 2);
            tasksArr[Task.getTaskCount()] = new Deadline(parsedInput[0], parsedInput[1].substring(3));
        } else if (input[0].equalsIgnoreCase("event")) {
            String[] parsedInput = input[1].split("/", 3);
            tasksArr[Task.getTaskCount()] = new Event(parsedInput[0], parsedInput[1].substring(5), parsedInput[2].substring(3));
        } else if (input[0].equalsIgnoreCase("todo")) {
            tasksArr[Task.getTaskCount()] = new Task(input[1]);
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + tasksArr[Task.getTaskCount()-1].toString());
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
    }

    private static void completeTask(String[] inputTokens) {
        int index = Integer.parseInt(inputTokens[1]) - 1;
        if (index >= 0 && index < Task.getTaskCount()) {
            tasksArr[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasksArr[index]);
        }
    }

    private static void uncompleteTask(String[] inputTokens) {
        int index = Integer.parseInt(inputTokens[1]) - 1;
        if (index >= 0 && index < Task.getTaskCount()) {
            tasksArr[index].markAsUndone();
            System.out.println("OK, I've marked this task as not done yet");
            System.out.println(tasksArr[index]);
        }
    }

}

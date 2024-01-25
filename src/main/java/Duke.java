import java.util.Scanner;
public class Duke {
    private static Task[] tasksArr = new Task[100];
    private static int taskCount = 0;

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
            addTask(input);
        }
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasksArr[i]);
        }
    }
    private static void addTask(String input) {
        tasksArr[taskCount] = new Task(input);
        System.out.println("added: " + input);
        taskCount++;
    }

    private static void completeTask(String[] inputTokens) {
        int index = Integer.parseInt(inputTokens[1]) - 1;
        if (index >= 0 && index < taskCount) {
            tasksArr[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasksArr[index]);
        }
    }

    private static void uncompleteTask(String[] inputTokens) {
        int index = Integer.parseInt(inputTokens[1]) - 1;
        if (index >= 0 && index < taskCount) {
            tasksArr[index].markAsUndone();
            System.out.println("OK, I've marked this task as not done yet");
            System.out.println(tasksArr[index]);
        }
    }

}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isDone;
    }
    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

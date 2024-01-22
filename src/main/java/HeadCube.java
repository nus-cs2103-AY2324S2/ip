import java.util.Scanner;
public class HeadCube {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] split = input.split(" ",2);

            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                list();
            } else if (split[0].equals("mark")){
                mark(Integer.parseInt(split[1]));

            } else {
                add(input);
            }

        }
    }

    public static void greet() {
        System.out.println("Hello! I'm HeadCube\n" + "What can I do for you?\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void add(String task) {
        String[] split = task.split(" ",2 );
        String event = split[0];
        String description;

        if (event.equals("todo")) {
            description = split[1];
            tasks[taskCount] = new ToDos(description);
        } else if (event.equals("deadline")) {
            String[] parts = split[1].split(" /by ",2);
            description = parts[0];
            String by = parts[1];
            tasks[taskCount] = new Deadlines(description,by);
        } else {
            String[] parts = split[1].split(" /from ", 2);
            description = parts[0];
            String[] times = parts[1].split(" /from ", 2);
            String start = times[0];
            String end = times[1];
            tasks[taskCount] = new Events(description, start, end);
        }

        System.out.println("Got it. I've added this task:\n  " + tasks[taskCount]);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.\n");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println();
    }

    public static void mark(int taskNumber) {
        Task task = tasks[taskNumber - 1];
        task.done();
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

}
import java.util.Scanner;

public class Bird {
    static Task[] taskList = new Task[100];
    static int index = 0;
    public static void main(String[] args) {
         
        System.out.println(" /\\_/\\");
        System.out.println("((@v@))");
        System.out.println("():::()");
        System.out.println(" VV-VV");
        System.out.println("What can I do for you?");
        Bird.scan();
    }

    private static void scan() {
        Scanner scanner = new Scanner(System.in);

        String markRegex = "^mark \\d+";
        String unmarkRegex = "^unmark \\d+";

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                Bird.list();
            } else if (userInput.matches(markRegex)) {
                Bird.markTask(Integer.parseInt(userInput.substring(5)));
            } else if (userInput.matches(unmarkRegex)) {
                Bird.unmarkTask(Integer.parseInt(userInput.substring(7)));
            }
            else {
                Task task = new Task(userInput);
                Bird.addTask(task);
            }
        }
        scanner.close();
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            int number = i + 1;
            System.out.println(number + ". " + taskList[i].toString2());
        }
    }

    public static void addTask(Task task) {
        taskList[index] = task;
        index += 1;
        System.out.println("added: " + task.toString());
    }

    public static void markTask(int idx) {
        Task task = taskList[idx-1];
        task.done();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString2());
    }

    public static void unmarkTask(int idx) {
        Task task = taskList[idx-1];
        task.undone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString2());
    }
}

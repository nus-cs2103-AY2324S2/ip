import java.util.*;

public class Duke {
    private static final String BOT_NAME = "Felix";
    private TaskList tasks;

    Duke() {this.tasks = new TaskList();}
    public void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) System.out.print('_');
        System.out.println();
    }
    public void printIntroduction() {
        printHorizontalLine(60);
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n",BOT_NAME);
        printHorizontalLine(60);
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        boolean loop = true;
        duke.printIntroduction();
        while (loop) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            duke.printHorizontalLine(60);
            if (words[0].equals("bye")) {
                duke.printGoodbye();
                loop = false;
            } else if (words[0].equals("list")) {
                System.out.println(duke.tasks);
            } else if (words[0].equals("mark")) {
                Task task = duke.tasks.getTask(Integer.parseInt(words[1]) - 1);
                task.markAsDone();
                System.out.println("Nice! I have marked this task as done:");
                System.out.println(task);
            } else if (words[0].equals("unmark")) {
                Task task = duke.tasks.getTask(Integer.parseInt(words[1]) - 1);
                task.unmarkDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
            } else {
                Task task = new Task(line);
                System.out.printf("added: %s\n", task);
                duke.tasks.addTask(task);
            }
            duke.printHorizontalLine(60);
        }
    }
}

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
        printHorizontalLine(60);
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
            duke.printHorizontalLine(60);
            if (line.equals("bye")) {
                loop = false;
                duke.printGoodbye();
            } else if (line.equals("list")) {
                System.out.println(duke.tasks);
                duke.printHorizontalLine(60);
            } else {
                Task task = new Task(line);
                System.out.printf("added: %s\n", task);
                duke.tasks.addTask(task);
                duke.printHorizontalLine(60);
            }
        }
    }
}

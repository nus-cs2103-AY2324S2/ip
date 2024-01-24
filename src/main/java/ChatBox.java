import java.util.Scanner;

public class ChatBox {
    Scanner scanner;
    String input;
    Task[] tasks;
    int taskCount;
    boolean isExitSignal;

    public ChatBox() {
        this.scanner = new Scanner(System.in);
        this.input = "";
        this.tasks = new Task[100];
        this.taskCount = 0;
        this.isExitSignal = false;
    }

    public void launch() {
        printGreet();
        while (!isExitSignal) {
            this.input = scanner.nextLine();
            parseInput();
        }
        printBye();
    }

    private void parseInput() {
        if (this.input.isEmpty()) {
            return;
        }
        if (this.input.equals("list")) {
            printList();
            return;
        }
        if (this.input.equals("bye")) {
            this.isExitSignal = true;
            return;
        }
        String[] words = input.split(" ");
        if (words.length == 2) {
            if (Parser.isMark(words)) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                mark(taskIndex);
                return;
            }
            if (Parser.isUnmark(words)) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                unmark(taskIndex);
                return;
            }
        }
        if (words[0].equals("todo")) {
            String description = Parser.parseTodo(this.input);
            addTodo(description);
            return;
        }
        if (words[0].equals("deadline")) {
            String[] parsedString = Parser.parseDeadline(this.input);
            addDeadline(parsedString[0], parsedString[1]);
            return;
        }
        if (words[0].equals("event")) {
            String[] parsedString = Parser.parseEvent(this.input);
            addEvent(parsedString[0], parsedString[1], parsedString[2]);
        }
    }

    private void printDecorator() {
        System.out.println("    ____________________________________________________________");
    }

    private void printGreet() {
        printDecorator();
        System.out.println("    Hello! I'm Wis.\n"
                + "    What can I do for you?");
        printDecorator();
    }

    private void addTodo(String description) {
        printDecorator();
        this.tasks[this.taskCount] = new Todo(description);
        this.taskCount++;
        System.out.println("    added: " + this.input);
        printDecorator();
    }

    private void addDeadline(String description, String time) {
        printDecorator();
        this.tasks[this.taskCount] = new Deadline(description, time);
        this.taskCount++;
        System.out.println("    added: " + this.input);
        printDecorator();
    }

    private void addEvent(String description, String beginTime, String endTime) {
        printDecorator();
        this.tasks[this.taskCount] = new Event(description, beginTime, endTime);
        this.taskCount++;
        System.out.println("    added: " + this.input);
        printDecorator();
    }

    private void printList() {
        printDecorator();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i+1) + "." + tasks[i].toString());
        }
        printDecorator();
    }

    private void printBye() {
        printDecorator();
        System.out.println("    Bye. Hope to see you again soon!");
        printDecorator();
    }

    private void mark(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskCount) {
            printDecorator();
            System.out.println("     Task index out of bound. Failed to mark.");
            printDecorator();
            return;
        }
        Task task = tasks[taskIndex];
        task.setDone();
        printDecorator();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        printDecorator();
    }

    private void unmark(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= taskCount) {
            printDecorator();
            System.out.println("     Task index out of bound. Failed to unmark.");
            printDecorator();
            return;
        }
        Task task = tasks[taskIndex];
        task.setUndone();
        printDecorator();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        printDecorator();
    }
}

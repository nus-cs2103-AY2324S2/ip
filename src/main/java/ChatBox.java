import java.util.InputMismatchException;
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
        if (words[0].equals("mark")) {
            mark(words);
            return;
        }
        if (words[0].equals("unmark")) {
            unmark(words);
            return;
        }
        if (words[0].equals("todo")) {
            addTodo();
            return;
        }
        if (words[0].equals("deadline")) {
            addDeadline();
            return;
        }
        if (words[0].equals("event")) {
            addEvent();
            return;
        }
        printDecorator();
        WisException.UnknownInputFormatExceptionHandler();
        printDecorator();
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

    private void addTodo() {
        try {
            String description = Parser.parseTodo(this.input);
            Todo todo = new Todo(description);
            this.tasks[this.taskCount] = todo;
            this.taskCount++;

            printDecorator();
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + todo);
            System.out.println("     Now you have " + this.taskCount + " tasks in the list.");
            printDecorator();
        } catch (InputMismatchException e) {
            printDecorator();
            System.out.println(e.getMessage());
            printDecorator();
        }
    }

    private void addDeadline() {
        try {
            String[] parsedString = Parser.parseDeadline(this.input);
            String description = parsedString[0];
            String time = parsedString[1];
            Deadline deadline = new Deadline(description, time);
            this.tasks[this.taskCount] = deadline;
            this.taskCount++;

            printDecorator();
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + deadline);
            System.out.println("     Now you have " + this.taskCount + " tasks in the list.");
            printDecorator();
        } catch (InputMismatchException e) {
            printDecorator();
            System.out.println(e.getMessage());
            printDecorator();
        }
    }

    private void addEvent() {
        try {
            String[] parsedString = Parser.parseEvent(this.input);
            String description = parsedString[0];
            String beginTime = parsedString[1];
            String endTime = parsedString[2];
            Event event = new Event(description, beginTime, endTime);
            this.tasks[this.taskCount] = event;
            this.taskCount++;

            printDecorator();
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + event);
            System.out.println("     Now you have " + this.taskCount + " tasks in the list.");
            printDecorator();
        } catch (InputMismatchException e) {
            printDecorator();
            System.out.println(e.getMessage());
            printDecorator();
        }
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

    private void mark(String[] words) {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            Task task = tasks[taskIndex];
            task.setDone();
            printDecorator();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + task);
            printDecorator();
        } catch (NullPointerException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: mark <task_index>");
            printDecorator();
        } catch (NumberFormatException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: mark <task_index>");
            printDecorator();
        } catch (ArrayIndexOutOfBoundsException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: mark <task_index>");
            printDecorator();
        }
    }

    private void unmark(String[] words) {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            Task task = tasks[taskIndex];
            task.setUndone();
            printDecorator();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + task);
            printDecorator();
        } catch (NullPointerException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: unmark <task_index>");
            printDecorator();
        } catch (NumberFormatException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: unmark <task_index>");
            printDecorator();
        } catch (ArrayIndexOutOfBoundsException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: unmark <task_index>");
            printDecorator();
        }
    }
}

import java.util.Scanner;

public class ChatBox {
    Scanner scanner;
    String input;
    String[] tasks;
    int taskCount;
    boolean isExitSignal;

    public ChatBox() {
        this.scanner = new Scanner(System.in);
        this.input = "";
        this.tasks = new String[100];
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
        if (this.input.equals("list")) {
            printList();
        } else if (this.input.equals("bye")) {
            this.isExitSignal = true;
        } else {
            addTask();
        }
    }

    private void printDecorator() {
        System.out.println("    ____________________________________________________________");
    }

    private void printGreet() {
        printDecorator();
        System.out.println("    Hello! I'm Wis.\n"
                + "    What can I do for you?\n");
        printDecorator();
    }

    private void addTask() {
        if (this.input.isEmpty()) {
            return;
        }
        printDecorator();
        this.tasks[this.taskCount] = this.input;
        this.taskCount++;
        System.out.println("    added: " + this.input);
        printDecorator();
    }

    private void printList() {
        printDecorator();
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i+1) + ". " + tasks[i]);
        }
        printDecorator();
    }

    private void printBye() {
        printDecorator();
        System.out.println("    Bye. Hope to see you again soon!");
        printDecorator();
    }



}

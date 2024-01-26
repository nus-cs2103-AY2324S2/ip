import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChatBox {
    Scanner scanner;
    String input;
    ArrayList<Task> tasks;
    int taskCount;
    boolean isExitSignal;

    public ChatBox() {
        this.scanner = new Scanner(System.in);
        this.input = "";
        this.tasks = new ArrayList<>();
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
        String[] words = input.split(" ");
        Action action = Parser.parseAction(this.input, words);
        switch (action) {
            case NONE:
                break;
            case ADD_TODO:
                addTodo();
                break;
            case ADD_DEADLINE:
                addDeadline();
                break;
            case ADD_EVENT:
                addEvent();
                break;
            case MARK:
                mark(words);
                break;
            case UNMARK:
                unmark(words);
                break;
            case DELETE:
                delete(words);
                break;
            case LIST:
                printList();
                break;
            case BYE:
                this.isExitSignal = true;
                break;
            default:
                printDecorator();
                WisException.UnknownInputFormatExceptionHandler();
                printDecorator();
        }
    }

    private void printDecorator() {
        System.out.println("    ____________________________________________________________");
    }

    private void printGreet() {
        printDecorator();
        System.out.println("     Hello! I'm Wis.\n"
                + "     What can I do for you?");
        printDecorator();
    }

    private void addTodo() {
        try {
            String description = Parser.parseTodo(this.input);
            Todo todo = new Todo(description);
            this.tasks.add(todo);
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
            this.tasks.add(deadline);
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
            this.tasks.add(event);
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
            System.out.println("     " + (i+1) + "." + tasks.get(i).toString());
        }
        printDecorator();
    }

    private void printBye() {
        printDecorator();
        System.out.println("     Bye. Hope to see you again soon!");
        printDecorator();
    }

    private void mark(String[] words) {
        try {
            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            task.setDone();

            printDecorator();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + task);
            printDecorator();
        } catch (IndexOutOfBoundsException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: mark <task_index>");
            printDecorator();
        } catch (NumberFormatException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: mark <task_index>");
            printDecorator();
        }
    }

    private void unmark(String[] words) {
        try {
            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            task.setUndone();

            printDecorator();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + task);
            printDecorator();
        } catch (IndexOutOfBoundsException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: unmark <task_index>");
            printDecorator();
        } catch (NumberFormatException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: unmark <task_index>");
            printDecorator();
        }
    }

    private void delete(String[] words) {
        try {
            Task task = tasks.remove(Integer.parseInt(words[1]) - 1);
            this.taskCount--;

            printDecorator();
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + task);
            System.out.println("     Now you have " + this.taskCount + " tasks in the list.");
            printDecorator();
        } catch (IndexOutOfBoundsException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: delete <task_index>");
            printDecorator();
        } catch (NumberFormatException e) {
            printDecorator();
            System.out.println("     Please enter a valid index.\n"
                    + "     Use this format: delete <task_index>");
            printDecorator();
        }
    }
}

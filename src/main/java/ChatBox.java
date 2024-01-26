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
        Printer.printActionAttach(Action.GREET);
        while (!isExitSignal) {
            this.input = scanner.nextLine();
            parseInput();
        }
        Printer.printActionAttach(Action.BYE);
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
                Printer.printActionAttach(Action.LIST, tasks, taskCount);
                break;
            case BYE:
                this.isExitSignal = true;
                break;
            case INVALID:
                WisException.ActionExceptionHandler(Action.INVALID);
                break;
            default:
                throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }

    private void addTodo() {
        try {
            Todo todo = new Todo(Parser.parseTodo(this.input));
            this.tasks.add(todo);
            this.taskCount++;
            Printer.printActionAttach(Action.ADD_TODO, todo, this.taskCount);
        } catch (InputMismatchException e) {
            WisException.ActionExceptionHandler(Action.ADD_TODO);
        }
    }

    private void addDeadline() {
        try {
            String[] parsedString = Parser.parseDeadline(this.input);
            Deadline deadline = new Deadline(parsedString[0], parsedString[1]);
            this.tasks.add(deadline);
            this.taskCount++;
            Printer.printActionAttach(Action.ADD_DEADLINE, deadline, this.taskCount);
        } catch (InputMismatchException e) {
            WisException.ActionExceptionHandler(Action.ADD_DEADLINE);
        }
    }

    private void addEvent() {
        try {
            String[] parsedString = Parser.parseEvent(this.input);
            Event event = new Event(parsedString[0], parsedString[1], parsedString[2]);
            this.tasks.add(event);
            this.taskCount++;
            Printer.printActionAttach(Action.ADD_EVENT, event, this.taskCount);
        } catch (InputMismatchException e) {
            WisException.ActionExceptionHandler(Action.ADD_EVENT);
        }
    }

    private void mark(String[] words) {
        try {
            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            task.setDone();
            Printer.printActionAttach(Action.MARK, task);
        } catch (IndexOutOfBoundsException e) {
            WisException.ActionExceptionHandler(Action.MARK);
        } catch (NumberFormatException e) {
            WisException.ActionExceptionHandler(Action.MARK);
        }
    }

    private void unmark(String[] words) {
        try {
            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            task.setUndone();
            Printer.printActionAttach(Action.UNMARK, task);
        } catch (IndexOutOfBoundsException e) {
            WisException.ActionExceptionHandler(Action.UNMARK);
        } catch (NumberFormatException e) {
            WisException.ActionExceptionHandler(Action.UNMARK);
        }
    }

    private void delete(String[] words) {
        try {
            Task task = tasks.remove(Integer.parseInt(words[1]) - 1);
            this.taskCount--;
            Printer.printActionAttach(Action.DELETE, task, this.taskCount);
        } catch (IndexOutOfBoundsException e) {
            WisException.ActionExceptionHandler(Action.DELETE);
        } catch (NumberFormatException e) {
            WisException.ActionExceptionHandler(Action.DELETE);
        }
    }
}

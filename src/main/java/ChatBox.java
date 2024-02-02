import java.io.IOException;
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
        try {
            this.tasks = FileManager.loadTasks();
            this.taskCount = this.tasks.size();
        } catch (IOException e) {
            WisException.LoadFileExceptionHandler();
            return;
        } catch (InputMismatchException e) {
            WisException.LoadFileExceptionHandler();
            return;
        }

        Printer.printActionAttach(Action.GREET);
        while (!isExitSignal) {
            this.input = scanner.nextLine();
            parseInput();
        }
        Printer.printActionAttach(Action.BYE);
    }

    private void parseInput() {
        String[] words = input.split(" ");
        Action action = InputParser.parseAction(this.input, words);
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
                Printer.printActionAttach(Action.LIST, this.tasks, this.taskCount);
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

    private void list() {
        try {
            this.tasks = FileManager.loadTasks();
            Printer.printActionAttach(Action.LIST, this.tasks, this.taskCount);
        } catch (IOException e) {
            WisException.LoadFileExceptionHandler();
        } catch (InputMismatchException e) {
            WisException.LoadFileExceptionHandler();
        } finally {
            Printer.printActionAttach(Action.LIST, this.tasks, this.taskCount);
        }
    }

    private void addTodo() {
        try {
            Todo todo = new Todo(InputParser.parseTodo(this.input));
            this.tasks.add(todo);
            this.taskCount++;
            FileManager.saveTasks(tasks);
            Printer.printActionAttach(Action.ADD_TODO, todo, this.taskCount);
        } catch (InputMismatchException e) {
            WisException.ActionExceptionHandler(Action.ADD_TODO);
        }
    }

    private void addDeadline() {
        try {
            String[] parsedString = InputParser.parseDeadline(this.input);
            Deadline deadline = new Deadline(parsedString[0], parsedString[1]);
            this.tasks.add(deadline);
            this.taskCount++;
            FileManager.saveTasks(tasks);
            Printer.printActionAttach(Action.ADD_DEADLINE, deadline, this.taskCount);
        } catch (InputMismatchException e) {
            WisException.ActionExceptionHandler(Action.ADD_DEADLINE);
        }
    }

    private void addEvent() {
        try {
            String[] parsedString = InputParser.parseEvent(this.input);
            Event event = new Event(parsedString[0], parsedString[1], parsedString[2]);
            this.tasks.add(event);
            this.taskCount++;
            FileManager.saveTasks(tasks);
            Printer.printActionAttach(Action.ADD_EVENT, event, this.taskCount);
        } catch (InputMismatchException e) {
            WisException.ActionExceptionHandler(Action.ADD_EVENT);
        }
    }

    private void mark(String[] words) {
        try {
            Task task = tasks.get(Integer.parseInt(words[1]) - 1);
            task.setDone();
            FileManager.saveTasks(tasks);
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
            FileManager.saveTasks(tasks);
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
            FileManager.saveTasks(tasks);
            Printer.printActionAttach(Action.DELETE, task, this.taskCount);
        } catch (IndexOutOfBoundsException e) {
            WisException.ActionExceptionHandler(Action.DELETE);
        } catch (NumberFormatException e) {
            WisException.ActionExceptionHandler(Action.DELETE);
        }
    }
}

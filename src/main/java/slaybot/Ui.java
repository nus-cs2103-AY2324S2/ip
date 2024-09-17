package slaybot;

import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.ToDo;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import exception.UnknownCommandException;

/**
 * The Ui class deals with interactions with the user
 */
public class Ui {
    public static final String DIVIDER = "____________________________________________________________";
    public static final String BYE_TEXT = "Bye. Hope to see you again soon!";
    private Parser parser;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for Ui class
     * @param tasks
     */
    public Ui(TaskList tasks) {
        this.parser = new Parser();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadData());
    }

    /**
     * Starts the user interface, allowing the user to interact with the SlayBot application.
     */
    public String start(String input) {
        String[] splitWords = input.split(" ");
        Parser.Command command = null;

        while (command == null) {
            try {
                command = this.parser.parseCommand(splitWords);
            } catch (UnknownCommandException e) {
                System.out.println(DIVIDER);
                System.out.println(e.getMessage());
                System.out.println("Enter a valid command");
                System.out.println(DIVIDER);
                return "Enter a valid command";

            }
        }

        assert command != null;

        switch (command) {
        case BYE:
            Storage.saveTasks(tasks);
            return BYE_TEXT;

        case LIST:
            return "Here are the tasks in your list:\n" + tasks.iterate();

        case TODO:
            ToDo todo;

            try {
                todo = this.parser.parseTodo(splitWords);
            } catch (InvalidTodoException e) {
                return DIVIDER + "\n" + e.getMessage() + "\nPlease try again" + "\n" + DIVIDER;
            }

            tasks.addTask(todo);
            storage.saveTasks(tasks);
            return "Todo Task Added: " + todo.toString() + "\n" + "You have " + tasks.getSize() + " tasks";

        case DEADLINE:
            Deadline deadline;

            try {
                deadline = this.parser.parseDeadline(splitWords);
            } catch (InvalidDeadlineException e) {
                return DIVIDER + "\n" + e.getMessage() + "\nPlease try again" + "\n" + DIVIDER;
            }

            tasks.addTask(deadline);
            storage.saveTasks(tasks);
            return "Deadline Task Added: " + deadline.toString() + "\nYou have " + tasks.getSize() + " tasks";

        case EVENT:
            Event event;

            try {
                event = this.parser.parseEvent(splitWords);
            } catch (InvalidEventException e) {
                return DIVIDER + "\n" + e.getMessage() + "\nPlease try again" + "\n" + DIVIDER;
            }

            tasks.addTask(event);
            storage.saveTasks(tasks);
            return "Event Task Added: " + event.toString() + "\nYou have " + tasks.getSize() + " tasks";


        case MARK:
            Task t = tasks.markTask(Integer.parseInt(splitWords[1]) - 1);
            storage.saveTasks(tasks);
            return "OK, I've marked this task as done:\n" + t.toString();


        case UNMARK:
            Task t1 = tasks.unmarkTask(Integer.parseInt(splitWords[1]) - 1);
            storage.saveTasks(tasks);
            return "OK, I've marked this task as not done yet:\n" + t1.toString();

        case DELETE:
            int indexToDelete = Integer.parseInt(splitWords[1]) - 1;

            return tasks.removeTask(indexToDelete);
        case FIND:
            return tasks.findTasks(splitWords[1]);
        case SORT:
            tasks.sortTask();
            return "Tasks have been sorted.";
        default:
            return "Enter a command to begin";
        }
    }
}


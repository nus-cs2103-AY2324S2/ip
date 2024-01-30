import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.ToDo;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import exception.UnknownCommandException;

import java.util.Scanner;

public class Ui {
    public static final String DIVIDER = "____________________________________________________________";
    public static final String WELCOME_TEXT = "Hello! I'm SlayBot\nWhat can I do for you?";
    public static final String BYE_TEXT = "Bye. Hope to see you again soon!";
    private Parser parser;
    private TaskList tasks;

    public Ui(TaskList tasks) {
        this.parser = new Parser();
        this.tasks = tasks;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        System.out.println(DIVIDER + "\n" + WELCOME_TEXT + "\n" + DIVIDER);

        while (flag) {
            String input = sc.nextLine();
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

                    input = sc.nextLine();
                    splitWords = input.split(" ");
                }
            }

            switch (command) {
                case BYE:
                    flag = false;
                    System.out.println(DIVIDER + "\n" + BYE_TEXT + "\n" + DIVIDER);
                    Storage.saveTasks(tasks);
                    break;

                case LIST:
                    System.out.println(DIVIDER);
                    System.out.println("Here are the tasks in your list:");
                    tasks.iterate();
                    System.out.println(DIVIDER);
                    Storage.saveTasks(tasks);
                    break;

                case TODO:
                    ToDo todo = null;
                    try {
                        todo = this.parser.parseTodo(splitWords);
                    } catch (InvalidTodoException e) {
                        System.out.println(DIVIDER + "\n" + e.getMessage() + "\nPlease try again" + "\n" + DIVIDER);
                        continue;
                    }
                    tasks.addTask(todo);

                    System.out.println(DIVIDER);
                    System.out.println("Todo Task Added: " + todo.toString());
                    System.out.println("You have " + tasks.getSize() + " tasks");
                    System.out.println(DIVIDER);
                    Storage.saveTasks(tasks);
                    break;

                case DEADLINE:
                    Deadline deadline = null;
                    try {
                        deadline = this.parser.parseDeadline(splitWords);
                    } catch (InvalidDeadlineException e) {
                        System.out.println(DIVIDER + "\n" + e.getMessage() + "\nPlease try again" + "\n" + DIVIDER);
                        continue;
                    }
                    tasks.addTask(deadline);

                    System.out.println(DIVIDER);
                    System.out.println("Deadline Task Added: " + deadline.toString());
                    System.out.println("You have " + tasks.getSize() + " tasks");
                    System.out.println(DIVIDER);
                    Storage.saveTasks(tasks);
                    break;

                case EVENT:
                    Event event = null;
                    try {
                        event = this.parser.parseEvent(splitWords);
                    } catch (InvalidEventException e) {
                        System.out.println(DIVIDER + "\n" + e.getMessage() + "\nPlease try again" + "\n" + DIVIDER);
                        continue;
                    }
                    tasks.addTask(event);

                    System.out.println(DIVIDER);
                    System.out.println("Event Task Added: " + event.toString());
                    System.out.println("You have " + tasks.getSize() + " tasks");
                    System.out.println(DIVIDER);
                    Storage.saveTasks(tasks);
                    break;

                case MARK:
                    tasks.markTask(Integer.parseInt(splitWords[1]) - 1);
                    Storage.saveTasks(tasks);
                    break;

                case UNMARK:
                    tasks.unmarkTask(Integer.parseInt(splitWords[1]) - 1);
                    Storage.saveTasks(tasks);
                    break;
                case DELETE:
                    try {
                        int indexToDelete = Integer.parseInt(splitWords[1]);
                        tasks.removeTask(indexToDelete - 1);
                        System.out.println(DIVIDER + "\n Successful deletion \n You now have " +
                                tasks.getSize() + " tasks\n" + DIVIDER);
                        Storage.saveTasks(tasks);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(DIVIDER + "\n Please input a valid index\n" + DIVIDER);
                        continue;
                    }
            }
        }
    }
}

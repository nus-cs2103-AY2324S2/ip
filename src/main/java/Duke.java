import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import DukeException.DukeException;
import commands.DeleteCommand;
import tasks.Event;
import tasks.Storage;
import tasks.TaskList;
import tasks.Todo;

public class Duke {

    private static final String TASKS_CACHE_PATH = ".duke-cache";
    public static TaskList tasks;
    private static final String HORIZONTALLINE= "---------------------------------\n";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    enum Instruction {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE
    }

    private static Instruction toInstruction(String input) throws DukeException {
        try {
            return Instruction.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Please enter instruction in the correct format"
                    + "\nHere are valid instructions: list, mark, unmark, deadline, event, todo");
        }
    }

    private static void greet() {
        String greet = "Hello! I'm Dino\n"
                + "What can I do for you?\n"
                + HORIZONTALLINE;
        System.out.println(greet);
    }

    public static void main(String[] args) throws DukeException{
        Storage storage = new Storage(TASKS_CACHE_PATH);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            System.out.println("Invalid Instruction: " + e.getMessage());
        }

        greet();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equalsIgnoreCase("bye")) {
            System.out.println(HORIZONTALLINE);
            try {
                processInput(input);
            } catch(DukeException e) {
                System.out.println("Invalid Instruction: " + e.getMessage());
            }
            storage.save(tasks);
            System.out.println(HORIZONTALLINE);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void processInput(String input) throws DukeException {
        String[] parsedInput = input.split(" ", 2);
        Instruction ins = toInstruction(parsedInput[0]);
        String details = parsedInput.length > 1 ? parsedInput[1] : "";

        switch (ins) {
            case LIST:
                listTasks();
                break;
            case MARK:
                completeTask(details);
                break;
            case UNMARK:
                uncompleteTask(details);
                break;
            case TODO:
                addTodo(details);
                break;
            case DEADLINE:
                addDeadline(details);
                break;
            case EVENT:
                addEvent(details);
                break;
            case DELETE:
                DeleteCommand.execute(details, tasks);
                break;
        }
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasks.get(i));
        }
    }


    private static void completeTask(String index) throws DukeException{
        if (index.length() < 1) { throw new DukeException(
            "Please enter the tasks number that you want to mark as incomplete: ex. mark 2"); }
        try {
            int i = Integer.parseInt(index) - 1;
            tasks.get(i).markAsDone();
            System.out.println("Nice! I've marked this tasks as done:");
            System.out.println(tasks.get(i).toString());
        } catch (Exception e) {
            throw new DukeException("Please enter the valid tasks number");
        }
    }

    private static void uncompleteTask(String index) throws DukeException{
        if(index.length() < 1) { throw new DukeException(
            "Please enter the tasks number that you want to mark as incomplete: ex. mark 2"); }

        try {
            int i = Integer.parseInt(index) - 1;
            tasks.get(i).markAsUndone();
            System.out.println("OK, I've marked this tasks as not done yet");
            System.out.println(tasks.get(i).toString());
        } catch(Exception e) {
            throw new DukeException("Please enter the valid tasks number");
        }
    }

    private static void addTodo(String details) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException("Please enter tasks description");
        }

        tasks.add(new Todo(details));
        System.out.println("Got it. I've added this tasks:");
        System.out.println("added: " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadline(String details) throws DukeException {
        String[] parsedInput = details.split("/by ", 2);
        if (parsedInput.length != 2) {
            throw new DukeException("Please enter tasks description and deadline"
                    + "\ncorrect format: deadline *tasks description* /by *deadline*");
        }

        try {
        tasks.add(new Deadline(parsedInput[0], LocalDateTime.parse(parsedInput[1]
                , dateTimeFormatter)));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date/Time or Date/Time is in wrong format"
                    + "\ncorrect format: dd/MM/yyyy HHmm");
        }
        System.out.println("Got it. I've added this tasks:");
        System.out.println("added: " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEvent(String details) throws DukeException{
        String[] parsedInput = details.split("/from ", 2);

        if (parsedInput.length != 2) {
            throw new DukeException("Please enter event description and time in the correct format"
                    + "\ncorrect format: event *event name* /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm");
        }

        String[] parsedDates= parsedInput[1].split(" /to ", 2);

        if (parsedDates.length != 2) {
            throw new DukeException("Please enter event description and time in the correct format"
                    + "\ncorrect format: event *event name* /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm");
        }

        System.out.println(parsedInput[0]);
        System.out.println(parsedDates[0]);
        System.out.println(parsedDates[1]);

        try {
            tasks.add(
                new Event(parsedInput[0], LocalDateTime.parse(parsedDates[0], dateTimeFormatter)
                        , LocalDateTime.parse(parsedDates[1], dateTimeFormatter)));
            System.out.println("Got it. I've added this tasks:");
            System.out.println("added: " + tasks.get(tasks.size() - 1).toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DukeException("Invalid Date/Time or Date/Time is in wrong format"
                    + "\ncorrect format: dd/MM/yyyy HHmm");
        }
    }

}

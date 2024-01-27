package kaiyap;

import exceptions.InvalidInputException;
import exceptions.KaiYapException;
import exceptions.MissingInputException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Ui ui;

    /**
     * Constructor for creating a TaskList object.
     *
     * @param ui The UI object that TaskList will interact with.
     */
    public TaskList(Ui ui) {
        tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on its index.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list based on its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Creates a task based on the input string given.
     *
     * @param input The input string from the user.
     * @return The task that is newly created.
     */
    public Task taskCreator(String input) {
        try {
            KaiYap.TaskType type;
            try {
                type = KaiYap.TaskType.valueOf(input.split(" ")[0].toUpperCase());
            } catch (Exception e) {
                throw new InvalidInputException("\tI don't quite understand what you mean. Please try again! UwU :3");
            }
            Task task = null;
            switch (type) {
                case TODO:
                    try {
                        task = createTodo(input);
                    } catch (KaiYapException e) {
                        ui.printError(e.getMessage());
                    }
                    break;
                case DEADLINE:
                    try {
                        task = createDeadline(input);
                    } catch (KaiYapException e) {
                        ui.printError(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        task = createEvent(input);
                    } catch (KaiYapException e) {
                        ui.printError(e.getMessage());
                    }
                    break;
            }
            if (task != null) {
                return task;
            }
        } catch (KaiYapException e) {
            ui.printError(e.getMessage());
        }
        return null;
    }

    /**
     * Creates a todo based on the input string given.
     *
     * @param input The input string from the user.
     * @return The Todo
     * that is newly created.
     */
    public Todo createTodo(String input) throws KaiYapException {
        if (input.equals("todo")) {
            throw new MissingInputException("\tYour todo needs a description. Please try again! UwU :3");
        } else {
            return new Todo(input.substring(input.indexOf(' ') + 1), input);
        }
    }

    /**
     * Creates a deadline based on the input string given.
     *
     * @param input The input string from the user.
     * @return The Deadline that is newly created.
     */
    public Deadline createDeadline(String input) throws KaiYapException {
        if (input.equals("deadline")) {
            throw new MissingInputException("Your deadline needs a description. Please try again! UwU :3");
        } else {
            try {
                return new Deadline(
                        input.substring(input.indexOf(" ") + 1, input.indexOf("/by")).strip(),
                        input,
                        LocalDateTime.parse(input.substring(input.indexOf("/by") + 3).strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                );
            } catch (DateTimeParseException d) {
                ui.printError("\tYour deadline format is wrong - please use the dd/MM/yyyy MMhh format! UwU :3");
                return null;
            } catch (Exception e) {
                throw new MissingInputException("\tYour deadline is missing some important information. Please try again! UwU :3");
            }
        }
    }

    /**
     * Creates an event based on the input string given.
     *
     * @param input The input string from the user.
     * @return The Event that is newly created.
     */
    public Event createEvent(String input) throws KaiYapException {
        if (input.equals("event")) {
            throw new MissingInputException("Your event needs a description. Please try again! UwU :3");
        } else {
            try {
                return new Event(
                        input.substring(input.indexOf(" ") + 1, input.indexOf("/from")).strip(),
                        input,
                        LocalDateTime.parse(input.substring(input.indexOf("/from") + 5, input.indexOf("/to")).strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                        LocalDateTime.parse(input.substring(input.indexOf("/to") + 3).strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                );
            } catch (DateTimeParseException d) {
                ui.printError("\tYour event timeline format is wrong - please use the dd/MM/yyyy MMhh format! UwU :3");
                return null;
            } catch (Exception e) {
                throw new MissingInputException("Your deadline is missing some important information. Please try again! UwU :3");
            }
        }
    }

}

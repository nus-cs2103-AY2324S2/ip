package duke;

public class Parser {

    /**
     * Parses and executes a given command.
     *
     * @param input   The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @throws DukeException If the command is invalid or execution fails.
     */
    public static void parseAndExecute(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (input.equals("bye")) {
            ui.showGoodbyeMessage();
            return;
        } else if (input.equals("list")) {
            ui.showTaskList(tasks);
            return;
        }

        String[] parts = input.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
            case "todo":
                addTodo(parts[1], tasks, ui, storage);
                break;
            case "deadline":
                addDeadline(parts[1], tasks, ui, storage);
                break;
            case "event":
                addEvent(parts[1], tasks, ui, storage);
                break;
            case "mark":
                markOrUnmarkTask(parts[1], tasks, ui, storage, true);
                break;
            case "unmark":
                markOrUnmarkTask(parts[1], tasks, ui, storage, false);
                break;
            case "delete":
                deleteTask(parts[1], tasks, ui, storage);
                break;
            default:
                throw new DukeException("Invalid Command. I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Adds a todo task to the list of tasks.
     *
     * @param description  The description of the todo task.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static void addTodo(String description, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        ui.showTaskAdded(todo, tasks.getSize());
        storage.save(tasks.getTasks());
    }

    /**
     * Adds a deadline task to the list of tasks.
     *
     * @param input  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static void addDeadline(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" /by ");
        if (splitInput.length < 2 || splitInput[0].isEmpty() || splitInput[1].isEmpty()) {
            throw new DukeException("The deadline description or date is missing.");
        }
        Deadline deadline = new Deadline(splitInput[0].trim(), splitInput[1].trim());
        tasks.addTask(deadline);
        ui.showTaskAdded(deadline, tasks.getSize());
        storage.save(tasks.getTasks());
    }

    /**
     * Adds a event task to the list of tasks.
     *
     * @param input  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static void addEvent(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" /from ");
        if (splitInput.length < 2 || splitInput[0].isEmpty() || splitInput[1].isEmpty()) {
            throw new DukeException("The event description or start time is missing.");
        }
        String[] timeSplit = splitInput[1].split(" /to ");
        if (timeSplit.length < 2 || timeSplit[0].isEmpty() || timeSplit[1].isEmpty()) {
            throw new DukeException("The event end time or details are missing.");
        }
        Event event = new Event(splitInput[0].trim(), timeSplit[0].trim(), timeSplit[1].trim());
        tasks.addTask(event);
        ui.showTaskAdded(event, tasks.getSize());
        storage.save(tasks.getTasks());
    }

    /**
     * Marks or unmarks a task in the list of tasks.
     *
     * @param input  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static void markOrUnmarkTask(String input, TaskList tasks, Ui ui, Storage storage, boolean isMark) throws DukeException {
        try {
            int idx = Integer.parseInt(input) - 1;
            Task task = tasks.getTask(idx);
            if (isMark) {
                task.markAsDone();
                ui.showMarkTask(task);
            } else {
                task.unmark();
                ui.showUnmarkTask(task);
            }
            storage.save(tasks.getTasks());
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a valid number.");
        }
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param input  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static void deleteTask(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int idx = Integer.parseInt(input) - 1;
            Task removedTask = tasks.removeTask(idx);
            ui.showTaskDeleted(removedTask, tasks.getSize());
            storage.save(tasks.getTasks());
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a valid number.");
        }
    }
}

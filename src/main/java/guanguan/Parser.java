package guanguan;

/**
 * Responsible for parsing user input
 */
public class Parser {
    /**
     * Parses user input and executes the command.
     *
     * @param input user input
     * @param items TaskList class
     * @param ui Ui class
     * @return false if user ends the program, else false
     * @throws GGException if user input is invalid
     */
    public static boolean parse(String input, TaskList items, Ui ui) throws GGException {
        String command = input.split(" ")[0];
        switch (command) {
        case "bye":
            ui.bye();
            return false;
        case "list":
            ui.tasks(items);
            break;
        case "mark":
            markTask(input, items, ui, true);
            break;
        case "unmark":
            markTask(input, items, ui, false);
            break;
        case "todo":
            addTodoTask(input, items, ui);
            break;
        case "deadline":
            addDeadlineTask(input, items, ui);
            break;
        case "event":
            addEventTask(input, items, ui);
            break;
        case "delete":
            deleteTask(input, items, ui);
            break;
        case "find":
            findTask(input, items, ui);
            break;
        default:
            throw new GGException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    /**
     * Marks a task as done or undone.
     *
     * @param input user input
     * @param items TaskList class
     * @param ui Ui class
     * @param isMark true if mark task, otherwise false
     * @throws GGException if user input is invalid
     */
    static void markTask(String input, TaskList items, Ui ui, boolean isMark) throws GGException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;

            if (isMark) {
                items.get(index).markDone();
                ui.markTask();
            } else {
                items.get(index).unmarkDone();
                ui.unmarkTask();
            }
            ui.println(items.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new GGException("Invalid task ID to mark");
        }
    }

    /**
     * Adds todo task.
     *
     * @param input user input
     * @param items TaskList class
     * @param ui Ui class
     * @throws GGException if description of todo is empty
     */
    static void addTodoTask(String input, TaskList items, Ui ui) throws GGException {
        if (input.length() <= 5) {
            throw new GGException("OOPS!!! The description of a todo cannot be empty.");
        }

        ui.addTask();

        Todo todo = new Todo(input.substring(5));

        items.add(todo);

        ui.println(todo.toString());
        ui.countTasks(items.size());
    }

    /**
     * Adds deadline task.
     *
     * @param input user input
     * @param items TaskList class
     * @param ui Ui class
     * @throws GGException if description of deadline is empty
     */
    static void addDeadlineTask(String input, TaskList items, Ui ui) throws GGException {
        if (input.length() <= 9) {
            throw new GGException("OOPS!!! The description of a deadline cannot be empty.");
        }

        try{
            String[] splittedCommand = input.split(" /by ");
            String task = splittedCommand[0].substring(9);
            String by = splittedCommand[1];

            Deadline deadline = new Deadline(task, Utils.convertStringToDateTime(by));
            items.add(deadline);

            ui.addTask();
            ui.println(deadline.toString());
            ui.countTasks(items.size());;
        } catch (IndexOutOfBoundsException e) {
            throw new GGException("Use /by to specify deadline.");
        }
    }

    /**
     * Adds event task.
     *
     * @param input user input
     * @param items TaskList class
     * @param ui Ui class
     * @throws GGException if description of event is empty
     */
    static void addEventTask(String input, TaskList items, Ui ui) throws GGException {
        if (input.length() <= 6) {
            throw new GGException("OOPS!!! The description of a event cannot be empty.");
        }

        try {
            String[] splittedCommand = input.split(" /from ");
            String task = splittedCommand[0].substring(6);

            String[] splittedTime = splittedCommand[1].split(" /to ");
            String from = splittedTime[0];
            String to = splittedTime[1];

            Event event = new Event(task, Utils.convertStringToDateTime(from),
                    Utils.convertStringToDateTime(to));
            items.add(event);

            ui.addTask();
            ui.println(event.toString());
            ui.countTasks(items.size());;
        } catch (IndexOutOfBoundsException e) {
            throw new GGException("Invalid event date. Use /from and /to");
        }
    }

    /**
     * Deletes a task.
     *
     * @param input user input
     * @param items TaskList class
     * @param ui Ui class
     * @throws GGException if task ID is invalid
     */
    static void deleteTask(String input, TaskList items, Ui ui) throws GGException {
        if (input.length() <= 7) {
            throw new GGException("OOPS!!! Task ID cannot be empty.");
        }

        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;

            Task task = items.remove(index);
            ui.deleteTask();
            ui.println(task.toString());
            ui.countTasks(items.size());;
        } catch (IndexOutOfBoundsException e) {
            throw new GGException("Invalid task ID to delete");
        }
    }

    /**
     * Finds task.
     *
     * @param input user input
     * @param items TaskList class
     * @param ui Ui class
     * @throws GGException if keyword is empty
     */
    static void findTask(String input, TaskList items, Ui ui) throws GGException {
        if (input.length() <= 5) {
            throw new GGException("OOPS!!! Keyword cannot be empty.");
        }

        String keyword = input.split(" ")[1];

        TaskList filteredItems = items.find(keyword);
        ui.tasks(filteredItems);
    }
}

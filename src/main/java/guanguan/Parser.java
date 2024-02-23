package guanguan;

import java.util.List;

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
     * @throws GgException if user input is invalid
     */
    public static boolean parse(String input, TaskList items, Ui ui) throws GgException {
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
            throw new GgException("OOPS!!! I'm sorry, but I don't know what that means :-(");
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
     * @throws GgException if user input is invalid
     */
    static void markTask(String input, TaskList items, Ui ui, boolean isMark) throws GgException {
        try {
            String parsedIndex = input.split(" ")[1];
            List<Integer> indexToMark = Utils.reverse(parsedIndex.split(","));

            for (int i : indexToMark) {
                int index = i - 1;
                if (isMark) {
                    items.get(index).markDone();
                    ui.markTask();
                } else {
                    items.get(index).unmarkDone();
                    ui.unmarkTask();
                }
                ui.println(items.get(index).toString());
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GgException("Invalid task ID to mark");
        }
    }

    /**
     * Adds todo task.
     *
     * @param input user input
     * @param items TaskList class
     * @param ui Ui class
     * @throws GgException if description of todo is empty
     */
    static void addTodoTask(String input, TaskList items, Ui ui) throws GgException {
        if (input.length() <= 5) {
            throw new GgException("OOPS!!! The description of a todo cannot be empty.");
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
     * @throws GgException if description of deadline is empty
     */
    static void addDeadlineTask(String input, TaskList items, Ui ui) throws GgException {
        if (input.length() <= 9) {
            throw new GgException("OOPS!!! The description of a deadline cannot be empty.");
        }

        try {
            String[] splittedCommand = input.split(" /by ");
            String task = splittedCommand[0].substring(9);
            String by = splittedCommand[1];

            Deadline deadline = new Deadline(task, Utils.convertStringToDateTime(by));
            items.add(deadline);

            ui.addTask();
            ui.println(deadline.toString());
            ui.countTasks(items.size());;
        } catch (IndexOutOfBoundsException e) {
            throw new GgException("Use /by to specify deadline.");
        }
    }

    /**
     * Adds event task.
     *
     * @param input user input
     * @param items TaskList class
     * @param ui Ui class
     * @throws GgException if description of event is empty
     */
    static void addEventTask(String input, TaskList items, Ui ui) throws GgException {
        if (input.length() <= 6) {
            throw new GgException("OOPS!!! The description of a event cannot be empty.");
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
            throw new GgException("Invalid event date. Use /from and /to");
        }
    }

    /**
     * Deletes a task.
     *
     * @param input user input
     * @param items TaskList class
     * @param ui Ui class
     * @throws GgException if task ID is invalid
     */
    static void deleteTask(String input, TaskList items, Ui ui) throws GgException {
        if (input.length() <= 7) {
            throw new GgException("OOPS!!! Task ID cannot be empty.");
        }

        try {
            String parsedIndex = input.split(" ")[1];
            List<Integer> indexToDelete = Utils.reverse(parsedIndex.split(","));

            ui.deleteTask();
            for (int i : indexToDelete) {
                Task task = items.remove(i - 1);
                ui.println(task.toString());
            }
            ui.countTasks(items.size());;
        } catch (IndexOutOfBoundsException e) {
            throw new GgException("Invalid task ID to delete");
        }
    }

    /**
     * Finds task.
     *
     * @param input user input
     * @param items TaskList class
     * @param ui Ui class
     * @throws GgException if keyword is empty
     */
    static void findTask(String input, TaskList items, Ui ui) throws GgException {
        if (input.length() <= 5) {
            throw new GgException("OOPS!!! Keyword cannot be empty.");
        }

        String keyword = input.split(" ")[1];

        TaskList filteredItems = items.find(keyword);
        ui.tasks(filteredItems);
    }
}

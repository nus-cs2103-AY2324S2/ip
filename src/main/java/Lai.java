import lai.task.Deadline;
import lai.task.Event;
import lai.task.Task;
import lai.task.ToDo;
import lai.util.*;

import java.util.List;

public class Lai {

    private Storage storage = new Storage();
    private TaskList tasks;


    /**
     * Constructs a Lai object.
     */
    public Lai() {
        tasks = new TaskList(storage.readTasksFile());

        assert tasks.size() >= 0 : "TaskList initialization failed, tasks has a size of less than 0";
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return A string message indicating the task has been successfully marked.
     * @throws LaiException If the description is invalid or the operation fails.
     */
    public String markTask(String index) throws LaiException {
        Parser.checkDescription(index);

        int i = Integer.parseInt(index);
        Task t = tasks.get(i - 1);
        t.setDone();
        storage.updateTasksFile(tasks);

        return Ui.printTaskMarked(t);
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as done.
     * @return A string message indicating the task has been successfully marked.
     * @throws LaiException If the description is invalid or the operation fails.
     */
    public String unmarkTask(String index) throws LaiException {
        Parser.checkDescription(index);

        int i = Integer.parseInt(index);
        Task t = tasks.get(i - 1);
        t.setNotDone();
        storage.updateTasksFile(tasks);

        return Ui.printTaskUnmarked(t);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return A string message indicating the task has been successfully deleted.
     * @throws IndexOutOfBoundsException If the provided index is out of bounds.
     */
    public String deleteTask(String index) throws IndexOutOfBoundsException {
        int i = Integer.parseInt(index);
        Task t = tasks.get(i - 1);
        tasks.remove(t);
        storage.updateTasksFile(tasks);

        return Ui.printTaskDeleted(t);
    }

    /**
     * Adds a new deadline task.
     *
     * @param desc The description of the deadline, including the task description and the deadline date/time.
     * @return A string message indicating the deadline task has been successfully added.
     * @throws LaiException If the description is invalid or the operation fails.
     */
    public String addDeadline(String desc) throws LaiException {
        Parser.checkDescription(desc);

        // Assert that the description is not empty after checking
        assert !desc.isEmpty() : "Deadline creation failed, " +
                "description for new Deadline is empty";

        // Separating the deadline from description
        String[] descBy = desc.split(" /by ", 2);
        desc = descBy[0];
        String by = "";
        if (descBy.length > 1) {
            by = descBy[1];
        }

        Deadline newTask = new Deadline(desc, by);

        return Ui.printTaskAdded(newTask, tasks.add(newTask, storage));
    }

    /**
     * Adds a new to-do task.
     *
     * @param desc The description of the to-do task.
     * @return A string message indicating the to-do task has been successfully added.
     * @throws LaiException If the description is invalid or the operation fails.
     */
    public String addToDo(String desc) throws LaiException {
        Parser.checkDescription(desc);

        // Assert that the description is not empty after checking
        assert !desc.isEmpty() : "ToDo creation failed, " +
                "description for new ToDo is empty";

        ToDo newTask = new ToDo(desc);

        return Ui.printTaskAdded(newTask, tasks.add(newTask, storage));
    }

    /**
     * Adds a new event task.
     *
     * @param desc The description of the event, including the event description, start time, and end time.
     * @return A string message indicating the event task has been successfully added.
     * @throws LaiException If the description is invalid or the operation fails.
     */
    public String addEvent(String desc) throws LaiException {
        Parser.checkDescription(desc);

        // Assert that the description is not empty after checking
        assert !desc.isEmpty() : "Event creation failed, " +
                "description for new Event is empty";

        // Separating the start from description
        String[] descFrom = desc.split(" /from ", 2);
        desc = descFrom[0];
        String from = "";
        if (descFrom.length > 1) {
            from = descFrom[1];
        }
        // Separating the end out
        String[] fromTo = from.split(" /to ", 2);
        from = fromTo[0];
        String to = "";
        if (fromTo.length > 1) {
            to = fromTo[1];
        }

        Event newTask = new Event(desc, from, to);

        return Ui.printTaskAdded(newTask, tasks.add(newTask, storage));
    }

    /**
     * Finds and lists all tasks that contain the given keyword in their description.
     *
     * @param desc The keyword to search for in the task descriptions.
     * @return A string listing all tasks that match the search keyword.
     */
    public String findTask(String desc) {
        List<Task> result = tasks.find(desc);
        return Ui.listTasks(new TaskList(result));
    }

    /**
     * Processes the input and executes. This method parses the input to extract the command and its description, then
     * executes the relevant task operation. If the command is not recognized or if any error occurs during the
     * execution of the command, appropriate error messages are returned.
     *
     * @param input The full input from the user, which includes the command word
     *              and possibly a description or additional information required.
     * @return A string message indicating the result of the command execution.
     */
    public String getResponse(String input) {
        String[] parsedInput = Parser.parse(input);
        String command = parsedInput[0];
        String desc = parsedInput[1];

        // Assert that only the command and description are being parsed
        assert parsedInput.length <= 2 : "parsedInput contains more than 2 elements";

        try {
            switch (command) {
            case "mark":
                return markTask(desc);

            case "unmark":
                return unmarkTask(desc);

            case "delete":
                return deleteTask(desc);

            case "deadline":
                return addDeadline(desc);

            case "todo":
                return addToDo(desc);

            case "event":
                return addEvent(desc);

            case "list":
                return Ui.listTasks(tasks);

            case "find":
                return findTask(desc);

            default:
                throw new LaiException("I don't recognise this command at all. You likely made an extremely " +
                        "disappointing mistake, or I did. I can't tell for sure.");
            }
        } catch (LaiException e) {
            return Ui.printLaiException(e);
        } catch (NumberFormatException e) {
            return Ui.printNumberFormatException(e);
        } catch (IndexOutOfBoundsException e) {
            return Ui.printIndexOutOfBoundsException(e);
        }
    }
}

package kai;

import java.util.ArrayList;

/**
 * Represents a list of tasks with various operations such as marking, unmarking, listing, removing and adding tasks.
 * Provides methods to interact with and manipulate the list of tasks.
 */

public class TaskList {
    private Ui ui = new Ui();
    private ArrayList<Task> myList;

    /**
     * Constructs a TaskList instance with the provided list of tasks.
     *
     * @param myList The list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> myList) {
        this.myList = myList;
    }

    /**
     * Marks a specified task in the list as done.
     *
     * @param parts An array containing the command and task number.
     * @return A string representing the information about the marked task.
     */
    public String markList(String[] parts) {
        // Assumption: Command parts should be provided
        assert parts.length > 1 : "Command parts must be provided";
        // Assumption: Task list should not be empty when marking tasks
        assert !myList.isEmpty() : "Task list should not be empty";

        int index = Integer.parseInt(parts[1]) - 1;

        if (index >= 0 && index < myList.size()) {
            Task task = myList.get(index);
            task.markAsDone();
            return ui.markInfo(task);

        } else {
            return ui.invalidNum();
        }
    }

    /**
     * Marks a specified task in the list as not done.
     *
     * @param parts An array containing the command and task number.
     * @return A string representing the information about the unmarked.
     */
    public String unmarkList(String[] parts) {
        // Assumption: Command parts should be provided
        assert parts.length > 1 : "Command parts must be provided";
        // Assumption: Task list should not be empty when marking tasks
        assert !myList.isEmpty() : "Task list should not be empty";

        int index = Integer.parseInt(parts[1]) - 1;

        if (index >= 0 && index < myList.size()) {
            Task task = myList.get(index);
            task.markAsNotDone();
            return ui.unmarkInfo(task);

        } else {
            return ui.invalidNum();
        }

    }

    /**
     * Lists all tasks in the current list.
     * @return A string representing the list of tasks.
     */
    public String list() {
        StringBuilder result = new StringBuilder(ui.listDetails()); // Start with the UI details
        result.append(ui.separationLine());
        for (Task task : myList) {
            // Append each item from the loop to the result string
            result.append((myList.indexOf(task) + 1)).append(".").append(task).append("\n");
        }
        result.append(ui.separationLine()); // Append the separation line

        return result.toString(); // Convert StringBuilder to String and return
    }

    /**
     * Removes a specified task from the list.
     *
     * @param parts An array containing the command and the task number.
     * @return A string representing the information about the removed task.
     */
    public String remove(String[] parts) {
        int removed = Integer.parseInt(parts[1]) - 1;
        if (removed >= 0 && removed < myList.size()) {
            Task item = myList.get(removed);
            myList.remove(removed);
            return ui.removeTop(item) + ui.removeBottom(myList.size());
        } else {
            return ui.invalidNum();
        }
    }

    /**
     * Finds tasks containing a specified keyword and prints them to the console.
     * @param parts An array containing the command and the keyword to search for.
     * @return      A string representing the list of tasks containing the keyword.
     */
    public String find(String[] parts) {
        StringBuilder result = new StringBuilder(ui.findListDetails()); // Start with the UI details

        String keyword = parts[1];
        result.append(ui.separationLine());
        for (Task task : myList) {
            if (task.getDescription().contains(keyword)) {
                result.append((myList.indexOf(task) + 1))
                        .append(".").append(task).append("\n"); // Append each item from the loop to the result string
            }
        }
        result.append(ui.separationLine()); // Append the separation line

        return result.toString(); // Convert StringBuilder to String and return
    }


    public String help() {
        return ui.help();
    }

    /**
     * Adds a task to the task list based on the given command and input.
     *
     * @param command       The command specifying the type of task to add.
     * @param restOfInputs  The additional information needed to create the task.
     * @return              A string representing the result of the operation.
     */
    public String add(String command, String restOfInputs) {
        try {
            if (command.equals("todo")) {
                return handleTodoCommand(restOfInputs);
            } else if (command.equals("deadline")) {
                return handleDeadlineCommand(restOfInputs);
            } else if (command.equals("event")) {
                return handleEventCommand(restOfInputs);
            } else {
                throw new KaiException(ui.displayErrorMessage());
            }
        } catch (KaiException e) {
            ui.errorEncounter(e);
        }
        return ui.blank();
    }

    /**
     * Handles the 'todo' command by creating a new Todo task and adding it to the task list.
     *
     * @param restOfInputs      The description of the Todo task.
     * @return                  A message indicating what todo task is added into the task and the number of tasks.
     * @throws KaiException    If the description is empty or null.
     */
    private String handleTodoCommand(String restOfInputs) throws KaiException {
        if (restOfInputs == null) {
            throw new KaiException(ui.displayEmptyErrorMessage());
        }

        Todo newTodo = new Todo(restOfInputs);
        myList.add(newTodo);
        int size = myList.size();
        return ui.todoInfo(newTodo, size);
    }

    /**
     * Handles the 'deadline' command by creating a new Deadline task and adding it to the task list.
     *
     * @param restOfInputs      The description and deadline of the Deadline task.
     * @return                  A message indicating what deadline task is added into the task and the number of task.
     * @throws KaiException    If the description or deadline is empty or null.
     */
    private String handleDeadlineCommand(String restOfInputs) throws KaiException {
        if (restOfInputs == null) {
            throw new KaiException(ui.displayEmptyErrorMessage());
        }

        String[] item = restOfInputs.split("/by");
        String items = item[0];
        String time = item[1];

        Deadline newDeadline = new Deadline(items, time);
        myList.add(newDeadline);
        int size = myList.size();
        return ui.deadlineInfo(newDeadline, size);
    }

    /**
     * Handles the 'event' command by creating a new Event task and adding it to the task list.
     *
     * @param restOfInputs      The description, start, and end time of the Event task.
     * @return                  A message indicating what event task is added into the task and the number of task left.
     * @throws KaiException    If the description, start time, or end time is empty or null.
     */
    private String handleEventCommand(String restOfInputs) throws KaiException {
        if (restOfInputs == null) {
            throw new KaiException(ui.displayEmptyErrorMessage());
        }

        String[] item = restOfInputs.split("/from");
        String items = item[0];
        String time = item[1];
        String[] period = time.split("/to");
        String from = period[0];
        String to = period[1];
        Event newEvent = new Event(items, from, to);
        myList.add(newEvent);
        int size = myList.size();
        return ui.eventInfo(newEvent, size);
    }
}

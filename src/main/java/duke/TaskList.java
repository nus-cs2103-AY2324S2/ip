package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks with various operations such as marking, unmarking, listing, removing and adding tasks.
 * Provides methods to interact with and manipulate the list of tasks.
 */

public class TaskList {

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
        //System.out.println("");
        int index = Integer.parseInt(parts[1]) - 1;
        Ui ui = new Ui();

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
        //System.out.println("");
        int index = Integer.parseInt(parts[1]) - 1;
        Ui ui = new Ui();

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
        Ui ui = new Ui();
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
        Ui ui = new Ui();
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
     * @return A string representing the list of tasks containing the keyword.
     */
    public String find(String[] parts) {
        Ui ui = new Ui();
        StringBuilder result = new StringBuilder(ui.findListDetails()); // Start with the UI details

        String keyword = parts[1];

        for (Task task : myList) {
            if (task.getDescription().contains(keyword)) {
                result.append((myList.indexOf(task) + 1))
                        .append(".").append(task).append("\n"); // Append each item from the loop to the result string
            }
        }
        result.append(ui.separationLine()); // Append the separation line

        return result.toString(); // Convert StringBuilder to String and return
    }


    /**
     * Adds a new task to the task list based on the provided command and input.
     * @param command The command indicating the type of task to add (e.g., "todo", "deadline", "event").
     * @param restOfInputs The rest of the user input containing task details (e.g., task description, deadline).
     * @return A string representing the information about the added task.
     */
    public String add(String command, String restOfInputs) {
        Ui ui = new Ui();

        try {
            if (command.equals("todo")) {
                try {
                    if (restOfInputs == null) {
                        String errorMessage = ui.emptyErrorMessage();
                        throw new DukeException(errorMessage);

                    } else {
                        Todo newTodo = new Todo(restOfInputs);
                        myList.add(newTodo);
                        int size = myList.size();

                        return ui.todoInfo(newTodo, size);
                    }

                } catch (DukeException e) {
                    ui.errorEncounter(e);
                }

            } else if (command.equals("deadline")) {

                try {
                    if (restOfInputs == null) {
                        String errorMessage = ui.emptyErrorMessage();
                        throw new DukeException(errorMessage);

                    } else {

                        String[] item = restOfInputs.split("/by");
                        String items = item[0];
                        String time = item[1];

                        Deadline newDeadline = new Deadline(items, time);
                        myList.add(newDeadline);
                        int size = myList.size();

                        return ui.deadlineInfo(newDeadline, size);
                    }

                } catch (DukeException e) {
                    ui.errorEncounter(e);
                }

            } else if (command.equals("event")) {

                try {
                    if (restOfInputs == null) {
                        String errorMessage = ui.emptyErrorMessage();
                        throw new DukeException(errorMessage);

                    } else {

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

                } catch (DukeException e) {
                    ui.errorEncounter(e);
                }

            } else {
                throw new DukeException(ui.errorMessage());
            }

        } catch (DukeException e) {
            ui.errorEncounter(e);
        }
        return ui.blank();
    }
}

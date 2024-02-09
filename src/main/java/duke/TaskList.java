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
     */
    public void markList(String[] parts) {
        //System.out.println("");
        int index = Integer.parseInt(parts[1]) - 1;
        Ui ui = new Ui();

        if (index >= 0 && index < myList.size()) {
            Task task = myList.get(index);
            task.markAsDone();
            ui.markInfo(task);

        } else {
            ui.invalidNum();
        }
    }

    /**
     * Marks a specified task in the list as not done.
     *
     * @param parts An array containing the command and task number.
     */
    public void unmarkList(String[] parts) {
        //System.out.println("");
        int index = Integer.parseInt(parts[1]) - 1;
        Ui ui = new Ui();

        if (index >= 0 && index < myList.size()) {
            Task task = myList.get(index);
            task.markAsNotDone();
            ui.unmarkInfo(task);

        } else {
            ui.invalidNum();
        }

    }

    /**
     * Lists all tasks in the current list.
     */
    public void list() {
        Ui ui = new Ui();
        ui.listDetails();

        for (Task task : myList) {
            System.out.println((myList.indexOf(task) + 1) + "." + task);
        }
        ui.separationLine();
    }

    /**
     * Removes a specified task from the list.
     *
     * @param parts An array containing the command and the task number.
     */
    public void remove(String[] parts) {
        int removed = Integer.parseInt(parts[1]) - 1;
        Ui ui = new Ui();
        if (removed >= 0 && removed < myList.size()) {
            Task item = myList.get(removed);
            ui.removeTop(item);
            myList.remove(removed);
            ui.removeBottom(myList.size());
        } else {
            ui.invalidNum();
        }
    }

    /**
     * Finds tasks containing a specified keyword and prints them to the console.
     * @param parts An array containing the command and the keyword to search for.
     */
    public void find(String[] parts) {

        Ui ui = new Ui();
        ui.findListDetails();

        String keyword = parts[1];

        for (Task task : myList) {

            if (task.getDescription().contains(keyword)) {
                System.out.println((myList.indexOf(task) + 1) + "." + task);
            }
        }

        ui.separationLine();
    }

    /**
     * Adds a new task to the task list based on the provided command and input.
     * @param command The command indicating the type of task to add (e.g., "todo", "deadline", "event").
     * @param restOfInputs The rest of the user input containing task details (e.g., task description, deadline).
     */
    public void add(String command, String restOfInputs) {
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

                        ui.todoInfo(newTodo, size);
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

                        ui.deadlineInfo(newDeadline, size);
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

                        ui.eventInfo(newEvent, size);
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
    }
}

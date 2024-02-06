package duke;

import java.util.ArrayList;
import java.util.Arrays;

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
        int removed_item = Integer.parseInt(parts[1]) - 1;
        Ui ui = new Ui();

        
        if (removed_item >= 0 && removed_item < myList.size()) {
            Task item = myList.get(removed_item);
            ui.removeTop(item);
            myList.remove(removed_item);
            ui.removeBottom(myList.size());
    
        } else {
            ui.invalidNum();    
        }
        
    }

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

                        String[] item_time = restOfInputs.split("/by");
                        String items = item_time[0];
                        String time = item_time[1];

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

                        String[] item_time = restOfInputs.split("/from");
                        String items = item_time[0];
                        String time = item_time[1];
            
                        String[] from_to = time.split("/to");
                        String from = from_to[0];
                        String to = from_to[1];
            
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
 
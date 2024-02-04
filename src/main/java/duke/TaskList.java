package duke;

import java.util.ArrayList;
import java.util.Arrays;


public class TaskList {

    private ArrayList<Task> myList;
    
    public TaskList(ArrayList<Task> myList) {
        this.myList = myList;
    }

    // to MARK the list
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

    public void list() {
        Ui ui = new Ui();
        ui.listDetails();

        for (Task task : myList) {
            System.out.println((myList.indexOf(task) + 1) + "." + task);
        }
        ui.separationLiine();
    }

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

    public void add(String command, String restOfInputs) {
        Ui ui = new Ui();

        try {
            if(command.equals("todo")) {
                /*
                * Adds a Todo task to the list.
                * 
                * @param item The description of the Todo task
                * @throws DukeException if the description is empty
                */

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

                } catch(DukeException e) {
                    ui.errorEncounter(e);
                }

            } else if (command.equals("deadline")) {
                /**
                * Adds a Deadline task to the list
                * 
                * @param item The description of the deadline task
                * @param time The due date and time of the deadline task
                */

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

                } catch(DukeException e) {
                    ui.errorEncounter(e);
                }

            } else if (command.equals("event")) {
                /**
                * Adds an Event task to the list
                * 
                * @param item The description of the Event task
                * @param from The start time of the Event task
                * @para to The end time of the Event task
                */

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

                } catch(DukeException e) {
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
 
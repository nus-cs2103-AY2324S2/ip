package chingu.command;

import chingu.Ui;
import chingu.Storage;
import chingu.exception.DateException;
import chingu.exception.DeadlineException;
import chingu.exception.EventException;
import chingu.exception.ToDosException;
import chingu.task.TaskList;
import chingu.task.Task;
import chingu.task.ToDo;
import chingu.task.Event;
import chingu.task.Deadline;

import java.util.ArrayList;

public class AddCommand extends Command {
    private String taskType;

    private String description;

    public final String ACKNOWLEDGEMENT = "Got it. I've added this task:\n";


    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskType.equals("todo")) {
            try {
                return processToDos(description, tasks.tasks);
            } catch (ToDosException e){
                ui.showError(e.getMessage());
                return e.getMessage();
            }
        } else if (taskType.equals("event")) {
            try {
                return processEvents(description, tasks.tasks);
            } catch (EventException e){
                ui.showError(e.getMessage());
                return e.getMessage();
            } catch (DateException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                return processDeadline(description, tasks.tasks);
            } catch (DeadlineException e){
                ui.showError(e.getMessage());
                return e.getMessage();
            } catch (DateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String processToDos(String description, ArrayList<Task> task) throws ToDosException {
        if (description.isEmpty()) {
            throw new ToDosException("What todos do you need to record?");
        }
        String descriptionPriority = description;
        String [] divided = descriptionPriority.split("/priority", 2);
        if (divided.length < 2) {
            throw new ToDosException("Please insert the priority of the task - /priority High/Mid/low");
        }
        description = divided[0];
        String priority = divided[1].trim();
        Task new_task = new ToDo(description, priority);
        task.add(new_task);
        String length = "" + task.size();
        String Response = ACKNOWLEDGEMENT + "\t" + new_task.toString() + "\n" +
                "Now you have " + length + " tasks in the list.";
        return Response;
    }

    public String processDeadline(String description, ArrayList<Task> task) throws DeadlineException, DateException {
        if (description.isEmpty()) {
            throw new DeadlineException("What deadline do you need to record?");
        }
        String [] divided = description.split("/by", 2);
        checkDeadlineSplit(divided.length);
        String D = divided[0];
        String byPriority = divided[1].trim();
        divided = byPriority.split("/priority", 2);
        String by = divided[0].trim();
        String priority = divided[1].trim();
        if (priority.isEmpty()) {
            throw new DeadlineException("Please insert the priority of the task - /priority High/Mid/low");
        }
        boolean validateDate = by.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}");
        if (!validateDate) {
            throw new DateException("Invalid format of the date");
        }
        Task new_task =  new Deadline(D, by, priority);
        task.add(new_task);
        String length = "" + task.size();
        String Response = ACKNOWLEDGEMENT + "\t" + new_task.toString() + "\n" +
                "Now you have " + length + " tasks in the list.";
        return Response;
    }

    public String processEvents(String description, ArrayList<Task> task) throws EventException, DateException {
        if (description.isEmpty()) {
            throw new EventException("What event do you need to record?");
        }
        String [] divided = description.split("/from", 2);
        checkEventSplit(divided.length);
        String D = divided[0];
        String fromTo = divided[1];
        divided = fromTo.split("/to", 2);
        checkEventSplit(divided.length);
        String from = divided[0].trim();
        String toPriority = divided[1].trim();
        divided = toPriority.split("/priority", 2);
        checkEventSplit(divided.length);
        String to = divided[0].trim();
        String priority = divided[1].trim();
        validateDates(from, to);
        Task new_task = new Event(D, from, to, priority);
        task.add(new_task);
        String length = "" + task.size();
        String Response = ACKNOWLEDGEMENT + "\t" + new_task.toString() + "\n" +
                "Now you have " + length + " tasks in the list.";
        return Response;
    }

    public void checkDeadlineSplit(int length) throws DeadlineException {
        if (length < 2) {
            throw new DeadlineException("Please insert the priority of the task - /priority High/Mid/low");
        }
    }

    public void checkEventSplit(int length) throws EventException{
        if (length < 2) {
            throw new EventException("There is no event timeline!");
        }
    }

    public void validateDates(String from, String to) {
        boolean validateFromDate = from.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}\\[0-9]{4}");
        assert !validateFromDate : "format of the event start date is WRONG!";
        boolean validateToDate = to.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}\\[0-9]{4}");
        assert !validateToDate : "format of the event end date is WRONG!";
    }

    public boolean isExit() {
        return false;
    }

}

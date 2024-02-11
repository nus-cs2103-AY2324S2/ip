package duke.command;

import duke.*;
import duke.task.*;

import java.util.ArrayList;

public class AddCommand extends Command {
    private String taskType;

    private String description;

    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskType.equals("todo")) {
            try {
                System.out.println(description);
                processToDos(description, tasks.tasks);
            } catch (ToDosException e){
                ui.showError(e.getMessage());
            }
        } else if (taskType.equals("event")) {
            try {
                processEvents(description, tasks.tasks);
            } catch (EventException e){
                ui.showError(e.getMessage());
            } catch (DateException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                processDeadline(description, tasks.tasks);
            } catch (DeadlineException e){
                ui.showError(e.getMessage());
            } catch (DateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void processToDos(String description, ArrayList<Task> task) throws ToDosException {
        if (description.isEmpty()) {
            throw new ToDosException("What todos do you need to record?");
        }
        Task new_task = new ToDos(description);
        task.add(new_task);
        String length = "" + task.size();
        System.out.println("Got it. I've added this task:\n" +
                "\t" + new_task.toString());
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    public static void processDeadline(String description, ArrayList<Task> task) throws DeadlineException, DateException {
        if (description.isEmpty()) {
            throw new DeadlineException("What deadline do you need to record?");
        }
        String [] divided = description.split("/by", 2);
        if (divided.length < 2) {
            throw new DeadlineException("When do you have to get it done");
        }
        String D = divided[0];
        String by = divided[1].trim();
        boolean validateDate = by.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}");
        if (!validateDate) {
            throw new DateException("Invalid format of the date");
        }
        Task new_task =  new Deadline(D, by);
        task.add(new_task);
        String length = "" + task.size();
        System.out.println("Got it. I've added this task:\n" +
                "\t" + new_task.toString());
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    public static void processEvents(String description, ArrayList<Task> task) throws EventException, DateException {
        if (description.isEmpty()) {
            throw new EventException("What event do you need to record?");
        }
        String [] divided = description.split("/from", 2);
        if (divided.length < 2) {
            throw new EventException("There is no event timeline!");
        }
        String D = divided[0];
        String fromTo = divided[1];
        divided = fromTo.split("/to", 2);
        if (divided.length < 2) {
            throw new EventException("There is no event timeline!");
        }
        String from = divided[0].trim();
        String to = divided[1].trim();
        boolean validateFromDate = from.matches("[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{4}");
        if (!validateFromDate) {
            throw new DateException("Invalid format of the date");
        }
        boolean validateToDate = to.matches("[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{4}");
        if (!validateToDate) {
            throw new DateException("Invalid format of the date");
        }
        Task new_task = new Events(D, from, to);
        task.add(new_task);
        String length = "" + task.size();
        System.out.println("Got it. I've added this task:\n" +
                "\t" + new_task.toString());
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    public boolean isExit() {
        return false;
    }

}

package chingu.command;

import chingu.Ui;
import chingu.Storage;
import chingu.Parser;
import chingu.exception.DateException;
import chingu.exception.DeadlineException;
import chingu.exception.EventException;
import chingu.exception.ToDosException;
import chingu.task.TaskList;
import chingu.task.Task;

/**
 * Class that deals with AddCommand from the User
 */
public class AddCommand extends Command {
    private String taskType;

    private String description;


    /**
     * Constructor for AddCommand
     *
     * @param taskType
     * @param description
     */
    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    /**
     * Executes the AddCommand by the user
     *
     * @param tasks that contains current list of tasks
     * @param ui that handles the Ui of the Chingu Program
     * @param storage that deals with loading or saving the file
     * @return the response of the Chingu program from the command
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskType.equals("todo")) {
            try {
                return processToDos(description, tasks, ui);
            } catch (ToDosException e){
                return ui.showError(e.getMessage());
            }
        } else if (taskType.equals("event")) {
            try {
                return processEvents(description, tasks, ui);
            } catch (EventException e){
                return ui.showError(e.getMessage());
            } catch (DateException e) {
                return ui.showDateError(e.getMessage());
            }
        } else {
            try {
                return processDeadline(description, tasks, ui);
            } catch (DeadlineException e){
                return ui.showError(e.getMessage());
            } catch (DateException e) {
                return ui.showDateError(e.getMessage());
            }
        }
    }

    /**
     * Process ToDo that will be added to the tasklist
     *
     * @param description of Todo
     * @param task contains list of tasks
     * @param ui that deals with Ui of the Chingu Program
     * @return response of the Chingu program from the command
     * @throws ToDosException if
     */
    public String processToDos(String description, TaskList task, Ui ui) throws ToDosException {
        Task new_task = Parser.parseToDos(description);
        task.add(new_task);
        String Response = ui.announceAcknowledgement() + "\t" + new_task.toString() + "\n" + task.getSize();
        return Response;
    }

    public String processDeadline(String description, TaskList task, Ui ui) throws DeadlineException, DateException {
        Task new_task = Parser.parseDeadline(description);
        task.add(new_task);
        String Response = ui.announceAcknowledgement() + "\t" + new_task.toString() + "\n" + task.getSize();;
        return Response;
    }

    public String processEvents(String description, TaskList task, Ui ui) throws EventException, DateException {
        Task new_task = Parser.parseEvent(description);
        task.add(new_task);
        String Response = ui.announceAcknowledgement() + "\t" + new_task.toString() + "\n" + task.getSize();
        return Response;
    }

    /**
     * Checks if it is last command of the Program (Exit Command - "bye")
     *
     * @return boolean that indicate if it is the last command from user
     */
    public boolean isExit() {
        return false;
    }

}

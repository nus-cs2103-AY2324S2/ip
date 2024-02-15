package tasklist.commands;

import java.time.DateTimeException;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;
import tasklist.tasks.Deadline;
import tasklist.tasks.EmptyDateException;
import tasklist.tasks.Event;
import tasklist.tasks.Task;
import tasklist.tasks.ToDo;

/**
 * Represents a command to add tasks of 4 different types - normal, todo, deadlines and event
 * Implements the Command interface.
 */
public class AddCommand implements Command {

    protected Task addedTask;
    private String input;
    private int number;

    /**
     * constructor for the add command.
     * @param string the string of the task to be added
     * @param number the type of task
     */
    public AddCommand(String input, int number) {
        // 0 - normal task, 1 - todo, 2 - deadline , 3 - event
        this.input = input;
        this.number = number;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        switch (number) {
            case 0:
                if (input.trim() != "") {
                    this.addedTask = new Task(input);
                } else {
                    return "Error, please enter a task.";
                }
                break;
                
            case 1:
                if (input.trim() != "") {
                    ToDo newToDo = new ToDo(input);
                    this.addedTask = newToDo;
                } else {
                    return "Error, please enter a task.";
                }
                break;

            case 2:
                try {
                    String[] theParts = input.split("/", 2);
                    if (!theParts[1].trim().startsWith("by")) {
                        return "Error: Deadline format is incorrect. Please include '/by' to specify the deadline. Example: 'Task Title /by Deadline'";
                    } 

                    Deadline newDeadline = new Deadline(theParts[0].trim(), theParts[1].trim());
                    this.addedTask = newDeadline;
                } catch (ArrayIndexOutOfBoundsException e) {
                    return("Error creating Deadline: Please enter a deadline.");
                } catch (DateTimeException | EmptyDateException e) {
                    return("Error creating Deadline: " + e.getMessage());
                }
                break;

            case 3: 
                try {
                    String[] theParts = input.split("/", 3);
                    if (!theParts[1].trim().startsWith("from")) { 
                        return "Error: Event format is incorrect. Please include '/from' to specify the Event." +
                        "\nExample: 'Task Title /from Start Date /to End Date' (in dd-mm-yyyy hhmm format)";
                    }

                    if (!theParts[2].trim().startsWith("to")) { 
                        return "Error: Event format is incorrect. Please include '/to' to specify the Event." +
                        "\nExample: 'Task Title /from Start Date /to End Date' (in dd-mm-yyyy hhmm format)";
                    }

                    Event newEvent = new Event(theParts[0].trim(), theParts[1].trim(), theParts[2].trim());
                    this.addedTask = newEvent;
                } catch (ArrayIndexOutOfBoundsException e) {
                    return("Error creating Event: Please format the input properly.");
                } catch (EmptyDateException | IllegalArgumentException | DateTimeException e) {
                    return("Error creating Event: " + e.getMessage());
                }

                break;

    
            default:
                return("error creating Task. Please contact the adminstrator.");
            }


        if (addedTask != null) {
            taskList.addTask(addedTask);
            return ui.showAddTaskMessage(addedTask, taskList.size());
        }
        return null;
    }
}

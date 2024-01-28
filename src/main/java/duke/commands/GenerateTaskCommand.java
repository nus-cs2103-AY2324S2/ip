package duke.commands;

import java.time.format.DateTimeParseException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

import duke.exceptions.TaskCreationException;

import duke.utils.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * This class implements a command that generates tasks based on TaskType.
 */
public class GenerateTaskCommand extends Command {
    /**
     * Enum for different Task types.
     */
    public static enum TaskType {TODO, DEADLINE, EVENT}
    
    private TaskType taskType;
    private String input;

    /**
     * Basic constructor for GenerateTaskCommand. 
     * 
     * @param taskType The type of task to be generated based on the enum TaskType.
     * @param input user input to be parsed.
     */
    public GenerateTaskCommand(TaskType taskType, String input) {
        super(false);
        this.taskType = taskType;
        this.input = input;
    }

    /** 
     * Method for executing task generation command. Generates task based on user input and taskType.
     * 
     * @param tasks the current list of tasks.
     * @param ui Ui object used by bot for printing information.
     * @param storage Storage object with save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) 
    throws TaskCreationException, DateTimeParseException {
        switch (this.taskType) {
        case TODO:
            Todo t = Todo.todoParse(false, input);
            tasks.add(t);
            ui.botPrint("Todo Task added!\n" + t.toString() + "\n" + 
                        "You now have " + tasks.size() + " tasks in the list.");
            break;
        case DEADLINE:
            Deadline d = Deadline.deadlineParse(false, input);
            tasks.add(d);
            ui.botPrint("Deadline Task added!\n" + d.toString() + "\n" + 
                        "You now have " + tasks.size() + " tasks in the list.");
            break;
        case EVENT:
            Event e = Event.eventParse(false, input);
            tasks.add(e);
            ui.botPrint("Event Task added!\n" + e.toString() + "\n" + 
                        "You now have " + tasks.size() + " tasks in the list.");
            break;
        }
    }
    
}

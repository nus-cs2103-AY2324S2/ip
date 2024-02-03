package duke.commands;

import java.time.format.DateTimeParseException;

import duke.exceptions.TaskCreationException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.utils.Storage;
import duke.utils.TaskList;


/**
 * This class implements a command that generates tasks based on TaskType.
 */
public class GenerateTaskCommand extends Command {
    /**
     * Enum for different Task types.
     */
    public static enum TaskType { TODO, DEADLINE, EVENT }

    private TaskType taskType;
    private String input;

    /**
     * Creates GenerateTaskCommand, basic constructor that takes in the type of task and the user input.
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
     * Executes task generation command, generates task based on user input and taskType.
     *
     * @param tasks the current list of tasks.
     * @param ui Ui object used by bot for printing information.
     * @param storage Storage object with save file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage)
            throws TaskCreationException, DateTimeParseException {
        switch (this.taskType) {
        case TODO:
            Todo t = Todo.todoParse(false, input);
            tasks.add(t);
            return "Todo Task added!\n" + t.toString() + "\n"
                   + "You now have " + tasks.size() + " tasks in the list.";
        case DEADLINE:
            Deadline d = Deadline.deadlineParse(false, input);
            tasks.add(d);
            return "Deadline Task added!\n" + d.toString() + "\n"
                   + "You now have " + tasks.size() + " tasks in the list.";
        case EVENT:
            Event e = Event.eventParse(false, input);
            tasks.add(e);
            return "Event Task added!\n" + e.toString() + "\n"
                   + "You now have " + tasks.size() + " tasks in the list.";
        default:
            return "Error generating task: No such task type";
        }
    }
}

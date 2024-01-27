package duke.commands;
import java.time.format.DateTimeParseException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.exceptions.TaskCreationException;
import duke.utils.TaskList;
import duke.tasks.Todo;
import duke.utils.Storage;
import duke.utils.Ui;

public class GenerateTaskCommand extends Command {
    public static enum TaskType {TODO, DEADLINE, EVENT}
    
    private TaskType taskType;
    private String input;

    public GenerateTaskCommand(TaskType taskType, String input) {
        super(false);
        this.taskType = taskType;
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) 
    throws TaskCreationException, DateTimeParseException {
        switch (this.taskType) {
        case TODO:
            Todo t = Todo.todoParse(false, input);
            tasks.add(t);
            ui.botPrint("Todo Task added!\n" + t.toString() + "\n" + "You now have " + tasks.size() + " tasks in the list.");
            break;
        case DEADLINE:
            Deadline d = Deadline.deadlineParse(false, input);
            tasks.add(d);
            ui.botPrint("Deadline Task added!\n" + d.toString() + "\n" + "You now have " + tasks.size() + " tasks in the list.");
            break;
        case EVENT:
            Event e = Event.eventParse(false, input);
            tasks.add(e);
            ui.botPrint("Event Task added!\n" + e.toString() + "\n" + "You now have " + tasks.size() + " tasks in the list.");
            break;
        }
    }
    
}

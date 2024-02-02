package duke.command;

import duke.util.Parser;
import duke.util.TaskList;

public abstract class Command {
    private final Parser.Cmd type;
    protected Command(Parser.Cmd type){
        this.type = type;
    }
    public abstract void run(TaskList taskList);
    public Parser.Cmd getType() {
        return this.type;
    }
}

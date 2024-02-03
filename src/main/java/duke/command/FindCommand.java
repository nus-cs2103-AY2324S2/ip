package duke.command;

import duke.util.Parser;
import duke.util.TaskList;

public class FindCommand  extends Command{
    String keyword;

    public FindCommand(Parser.Cmd type, String keyword) {
        super(type);
        this.keyword = keyword;
    }
    @Override
    public void run(TaskList taskList) {
        taskList.search(this.keyword);
    }
}
